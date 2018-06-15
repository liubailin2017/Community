package com.sto.asportclient.login;

import android.app.Activity;
import android.content.Intent;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.User;

public class LoginContract {

    public static interface View extends BaseView{

        /**
         * 显示加载框
         */
        public void showLoading();

        /**
         * 隐藏加载框
         */
        public void hideLoading();

        public void setUser_pwd(String user,String passwd);
        public void setIsAutoLogin(boolean isAutoLogin);
        public void setIsRemenber(boolean isRemenber);
        public boolean isCheckRemember();
        public boolean isAutoLogin();

        public void showMsg(String msg);

        public  void toActivity(Class< ? extends Activity> activity,User user);

        public void finish();
    }

    public static interface Presenter extends BasePresenter{
        public void login(String username,String password);
        public void remember(String username,String password);
        public void recovery();
    }

}
