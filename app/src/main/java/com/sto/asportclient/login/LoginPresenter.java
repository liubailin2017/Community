package com.sto.asportclient.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
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
                                view.showMsg("登录成功");
                                /**
                                 * 这里可以增加对应的跳转
                                 */
                                view.toActivity(MainPageAboutMe.class,user);
                                view.finish();
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

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }
}
