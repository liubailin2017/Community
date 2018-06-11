package com.sto.asportclient.adddyn;

import android.app.Activity;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;

import java.io.File;

public class AddDynContract {

    public static interface View extends BaseView {
        /**
         * 显示加载框
         */
        public void showLoading();

        /**
         * 隐藏加载框
         */
        public void hideLoading();

        public void showMsg(String msg);

        public void finshAct();

    }

    public static interface Presenter extends BasePresenter {
        public void addDyn(String title,String content,File img);
    }

}
