package com.sto.asportclient.mainpage;

import android.app.Activity;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.login.LoginContract;

public class MainPageContract {

    public static interface View extends BaseView{
        /**
         * 显示加载框
         */
        public void showLoading();

        /**
         * 隐藏加载框
         */
        public void hideLoading();

        public void showMsg(String msg);

        public  void toActivity(Class< ? extends Activity> activity, User user);

    }

    public static interface Presenter {
        public void setView(LoginContract.View view);
    }
}
