package com.sto.asportclient.login;

import android.content.Intent;

public class LoginContract {

    public static interface View {
        public void setPresenter(Presenter presenter);
        /**
         * 显示加载框
         */
        public void showLoading();

        /**
         * 隐藏加载框
         */
        public void hideLoading();

        /**
         * 跳转的指定的Activity
         * @param intent
         */
        public void toActivity(Intent intent);

        public void showErrMsg(String msg);
    }

    public static interface Presenter {
        public void login(String username,String password);
        public void setView(View view);
    }

}
