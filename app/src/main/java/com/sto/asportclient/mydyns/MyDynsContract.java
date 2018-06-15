package com.sto.asportclient.mydyns;

import android.app.Activity;
import android.content.Intent;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;

import java.io.Serializable;
import java.util.Map;

public class MyDynsContract {

    public static interface View extends BaseView {
        /**
         * 显示加载框
         */
        public void showRefreshing();
        /**
         * 隐藏加载框
         */
        public void hideRefreshing();
        public void hideLoadingBttom();
        public void showMsg(String msg);

        public  void toActivity(Class< ? extends Activity> activity, User user);
        public  void toActivity(Class< ? extends Activity> activity, User user, Map<String,Serializable> map);

        public void upDateShowData(Dyns.DynsBean dyns);

        public void addData(Dyns.DynsBean dyns);

        public void clearData();

    }

    public static interface Presenter extends BasePresenter {

        public void updateMydyn();

        /**
         * 获得下一页数据并显示的listview上（核心实现）
          */
        public void loadNext();


        public void deleteDyn(Long dynId);

        public User getUser();
    }

}
