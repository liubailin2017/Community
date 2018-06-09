package com.sto.asportclient.adddyn;

import android.app.Activity;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;

public class AddDynContract {

    public static interface View extends BaseView {
        /**
         * 显示加载框
         */
        public void showLoading();
        /**
         * 隐藏加载框
         */
        public void hideLoadingBttom();

        public void openImg();

        public void showMsg(String msg);

    }

    public static interface Presenter extends BasePresenter {
        public void addDyn();
    }

}
