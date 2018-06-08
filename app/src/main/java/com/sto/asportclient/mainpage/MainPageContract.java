package com.sto.asportclient.mainpage;

import android.app.Activity;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.Dyns.DynsBean;
import com.sto.asportclient.data.util.bean.User;

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

        public void upDateShowData(DynsBean dyns);
        public void upDateShowData2(DynsBean dyns);
        public void addData(DynsBean dyns);
        public void addData2(DynsBean dyns);

        public void clearData();
        public void clearData2();
    }

    public static interface Presenter extends BasePresenter {
        public void updateMydyn();

        public void updateClassmete();

    }
}
