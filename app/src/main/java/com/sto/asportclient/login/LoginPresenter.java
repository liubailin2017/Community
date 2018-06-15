package com.sto.asportclient.login;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.Config;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mainpage.MainPageAboutMe;

public class LoginPresenter implements LoginContract.Presenter {

    private Handler handler = new Handler(Looper.getMainLooper());

    private LoginContract.View view;
    private Repertory repertory = RepertoryImpl.getInstance();

    @Override
    public void login(final String username, final String password) {
        view.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                repertory.login(username, password, new Repertory.LoginListener() {
                    @Override
                    public void onSucceed(final User user) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.hideLoading();
                                if(view.isCheckRemember()) {
                                    remember(username,password);
                                }
                                view.showMsg("登录成功");
                                /**
                                 * 这里可以增加对应的跳转
                                 */
                                view.toActivity(MainPageAboutMe.class,user);
                                view.finish();
                                if(view.isCheckRemember())
                                    remember(username,password);
                                else
                                    repertory.getConfig(view.getContext()).clearUserAndPwd();
                                repertory.getConfig(view.getContext()).setIsAutoLogin(view.isAutoLogin());
                                repertory.getConfig(view.getContext()).setIsRememberUserPwd(view.isCheckRemember());
                            }
                        });
                    }

                    @Override
                    public void Failed(final String msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.hideLoading();
                                view.showMsg(msg);
                            }
                        });
                    }
                });
            }
        }).start();
    }

    @Override
    public void remember(String username, String password) {
        repertory.getConfig(view.getContext()).savaUserAndPwd(new Config.User(username,password));
    }

    @Override
    public void recovery() {
        Config.User user = repertory.getConfig(view.getContext()).getUserAndPwd();
        view.setIsAutoLogin(repertory.getConfig(view.getContext()).getIsAutoLogin());
        view.setIsRemenber(repertory.getConfig(view.getContext()).getIsRememberUserPwd());
        if(user != null) {
            view.setUser_pwd(user.user, user.pwd);
            if (repertory.getConfig(view.getContext()).getIsAutoLogin()) {
                login(user.user, user.pwd);
            }
        }
    }

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (LoginContract.View) view;
    }
}
