package com.sto.asportclient.mydyns;

import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.DelDynBean;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.MyToast;

public class MyDynsPresenter implements MyDynsContract.Presenter {
    private MyDynsContract.View view;
    private User user;
    private Handler handler = new Handler(Looper.getMainLooper());

    private int pageSize = 6;
    private int curPageNo = 1;

    public MyDynsPresenter(MyDynsContract.View view, User user) {
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
                repertory.getDynsSelect(Long.parseLong(user.getUser()),1,pageSize, new Repertory.GetDataListener<Dyns>() {
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


    @Override
    public void loadNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                repertory.getDynsSelect(Long.parseLong(user.getUser()),curPageNo+1,pageSize, new Repertory.GetDataListener<Dyns>() {
                    @Override
                    public void onSucceed(final Dyns data) {
                        curPageNo = data.getDyns().getPageNo();
                        pageSize = data.getDyns().getPageSize();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.addData(data.getDyns());
                                view.hideLoadingBttom();
                                if(curPageNo >= data.getDyns().getBottomPageNo()) {
                                    view.showMsg("没有更多数据了");
                                    curPageNo = data.getDyns().getBottomPageNo();
                                }
                                else
                                    view.showMsg("加载完成");
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
    public void deleteDyn(final Long dynId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                /**
                 * 这里是更新，不是加载，别看错了。
                 */
                repertory.deleteDyn(dynId, new Repertory.GetDataListener<DelDynBean>() {

                    @Override
                    public void onSucceed(DelDynBean data) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                updateMydyn();
                            }
                        });

                    }

                    @Override
                    public void Failed(final Repertory.FailedMsg msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.showMsg("删除失败："+msg);
                            }
                        });

                    }
                });
            }
        }).start();
    }

    @Override
    public User getUser() {
        return user;
    }


    @Override
    public void setView(BaseView view) {
        this.view = (MyDynsContract.View) view;
    }
}