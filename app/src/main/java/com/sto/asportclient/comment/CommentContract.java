package com.sto.asportclient.comment;

import android.app.Activity;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.util.bean.AddBean;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;

import java.util.ArrayList;
import java.util.List;

public class CommentContract {

    public static interface View extends BaseView {
        public void showMsg(String msg);
        public  void toActivity(Class<? extends Activity> activity, User user);
        public  void update(ArrayList<Comms.CommsBean> comms);
        public Dyns.DynsBean.DynBean getDynBean();

        public void showCommentEditor(Long comm_id,String msg);
        public void hideCommentEditor();

    }

    public static interface Presenter extends BasePresenter {
        public void addComments(long dynId, String content, Long com_Comment_id);
        public void getComments(long dyn);
        public void  update();
    }

}
