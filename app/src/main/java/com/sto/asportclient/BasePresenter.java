package com.sto.asportclient;

import com.sto.asportclient.login.LoginContract;

public interface BasePresenter {
    public void setView(LoginContract.View view);
}
