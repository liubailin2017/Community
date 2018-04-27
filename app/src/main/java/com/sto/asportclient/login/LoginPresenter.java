package com.sto.asportclient.login;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;

public class LoginPresenter implements LoginContract.Presenter {
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
                    public void onSucceed() {
                        view.hideLoading();
                        view.showErrMsg("成功");
                        /**
                         * 这里可以增加对应的跳转
                         */
                    }

                    @Override
                    public void Failed(String msg) {
                        view.hideLoading();
                       view.showErrMsg(msg);
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
