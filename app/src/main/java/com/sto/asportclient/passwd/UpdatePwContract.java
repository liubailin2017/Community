package com.sto.asportclient.passwd;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;

import java.io.File;

public class UpdatePwContract {
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

        public void toLoginAct();

    }

    public static interface Presenter extends BasePresenter {
        public void updatePw(String oldpw,String newPw, String reNewPw);
    }

}
