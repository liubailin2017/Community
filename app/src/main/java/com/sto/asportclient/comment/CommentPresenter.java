package com.sto.asportclient.comment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.AddBean;
import com.sto.asportclient.data.util.bean.Comms;

import java.util.ArrayList;

public class CommentPresenter implements CommentContract.Presenter {
    Handler handler = new Handler(Looper.getMainLooper());
    CommentContract.View view;
    Repertory repertory = RepertoryImpl.getInstance();
    private ArrayList<Comms.CommsBean> commsBeans = new ArrayList<>();

    public CommentPresenter(CommentContract.View view) {
        setView(view);
    }

    @Override
    public void setView(BaseView view) {
        this.view = (CommentContract.View) view;
    }

    @Override
    public void addComments(long dynId, String content, Long com_Comment_id) {
        repertory.getCommunityDatInstance().addComments(dynId, content, com_Comment_id, new Repertory.GetDataListener<AddBean>() {
            @Override
            public void onSucceed(AddBean data) {
                update();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.showMsg("回复成功");
                    }
                });

            }

            @Override
            public void Failed(final Repertory.FailedMsg msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.showMsg(msg.getMsg());
                    }
                });
            }
        });
    }

//    public void click(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RepertoryImpl.getInstance().getCommunityDatInstance().getComments(7, new Repertory.GetDataListener<Comms>() {
//                    @Override
//                    public void onSucceed(final Comms data) {
//                        Log.i("data",data.toString());
//                        ArrayList<Comms.CommsBean> arrayList = data.removeCommsForDyns();
//                        for(int i = 0; i< arrayList.size();i++) {
//                            Log.i(arrayList.get(i).getStu_nickName()+"回复"+arrayList.get(i).getForComms_nickName(),"+"+arrayList.get(i).getComment().getContent());
//                            data.removeCommsForComms(arrayList.get(i));
//                            ArrayList<Comms.CommsBean> arrayList2 = data.back();
//                            for(int j =0;j< arrayList2.size();j++) {
//                                Log.i(arrayList2.get(j).getStu_nickName()+"回复"+arrayList2.get(j).getForComms_nickName(),arrayList2.get(j).getComment().getContent());
//                            }
//                        }
//                    }
//                    @Override
//                    public void Failed(Repertory.FailedMsg msg) {
//
//                    }
//                });
//            }
//        }).start();
//    }

    @Override
    public void getComments(long dyn) {
        repertory.getCommunityDatInstance().getComments(dyn, new Repertory.GetDataListener<Comms>() {
            @Override
            public void onSucceed(Comms data) {
                commsBeans.clear();
                Log.i("comms",data.toString());
                ArrayList<Comms.CommsBean> arrayList = data.removeCommsForDyns();
                for(int i = 0; i< arrayList.size();i++) {
                    commsBeans.add(arrayList.get(i));
                    Log.i(arrayList.get(i).getStu_nickName()+"回复"+arrayList.get(i).getForComms_nickName(),"+"+arrayList.get(i).getComment().getContent());
                    data.removeCommsForComms(arrayList.get(i),0);
                    ArrayList<Comms.CommsBean> arrayList2 = data.back();
                    commsBeans.addAll(arrayList2);
//                    for(int j =0;j< arrayList2.size();j++) {
//                        Log.i(arrayList2.get(j).getStu_nickName()+"回复"+arrayList2.get(j).getForComms_nickName(),arrayList2.get(j).getComment().getContent());
//                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.update(commsBeans);
                    }
                });
            }

            @Override
            public void Failed(final Repertory.FailedMsg msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.showMsg(msg.getMsg());
                    }
                });
            }
        });
    }

    @Override
    public void update() {
       getComments(view.getDynBean().getDynId());
    }

}
