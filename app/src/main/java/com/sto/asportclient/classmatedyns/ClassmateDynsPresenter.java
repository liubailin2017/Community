package com.sto.asportclient.classmatedyns;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.MyToast;

public class ClassmateDynsPresenter implements ClassmateDynsContract.Presenter {
    private ClassmateDynsContract.View view;
    private User user;
    private Handler handler = new Handler(Looper.getMainLooper());

    public ClassmateDynsPresenter(ClassmateDynsContract.View view, User user) {
        this.user = user;
        this.view = view;
    }

    @Override
    public void updateMydyn() {
        view.showRefreshing();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                /**
                 * 这里是更新，不是加载，别看错了。
                 */
                repertory.getClassmateDyns(Long.parseLong(user.getUser()),1,6, new Repertory.GetDataListener<Dyns>() {
                    @Override
                    public void onSucceed(final Dyns data) {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.upDateShowData(data.getDyns());
                                view.hideRefreshing();
                                curPageNo = data.getDyns().getPageNo();
                                view.showMsg("更新完成");

                            }
                        });
                    }

                    @Override
                    public void Failed(final Repertory.FailedMsg msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.showMsg(msg.getMsg());
                                view.hideRefreshing();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    private int pageSize = 2;
    private int curPageNo = 1;

    @Override
    public void loadNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                repertory.getClassmateDyns(Long.parseLong(user.getUser()),curPageNo+1,pageSize, new Repertory.GetDataListener<Dyns>() {
                    @Override
                    public void onSucceed(final Dyns data) {
                        curPageNo = data.getDyns().getPageNo();
                        pageSize = data.getDyns().getPageSize();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                view.addData(data.getDyns());
                                if(curPageNo >= data.getDyns().getBottomPageNo()) {
                                    view.showMsg("没有更多数据了");
                                    curPageNo = data.getDyns().getBottomPageNo();
                                }
                                else
                                    view.showMsg("加载完成");
                                view.hideLoadingBttom();
                            }
                        });
                    }

                    @Override
                    public void Failed(final Repertory.FailedMsg msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.showMsg(msg.getMsg());
                                view.hideLoadingBttom();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    @Override
    public void setView(BaseView view) {
        this.view = (ClassmateDynsContract.View) view;
    }
}
