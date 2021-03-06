package com.sto.asportclient.mainpage;

import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mainpage.adapter.DynAdapter;


public class MainPagePresenter implements MainPageContract.Presenter {
    private MainPageContract.View view;
    private DynAdapter adapter;
    private DynAdapter adapter2;
    private Handler handler = new Handler(Looper.getMainLooper());
    private User user;

    public MainPagePresenter(MainPageContract.View view, User user) {
        setView(view);
        this.user = user;
        this.adapter = adapter;
        this.adapter2 = adapter2;
    }

    @Override
    public void setView(BaseView view) {
        this.view = (MainPageContract.View) view;
    }

    @Override
    public void updateMydyn() {
        view.showLoading1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                repertory.getDynsSelect(Long.parseLong(user.getUser()),1,5, new Repertory.GetDataListener<Dyns>() {
                    @Override
                    public void onSucceed(final Dyns data) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.upDateShowData(data.getDyns());
                                view.hideLoading1();
                            }
                        });
                    }

                    @Override
                    public void Failed(final Repertory.FailedMsg msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.showMsg(msg.getMsg());
                                view.hideLoading1();
                            }
                        });

                    }
                });
            }
        }).start();
    }

    @Override
    public void updateClassmete() {
        view.showLoading2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CommunityDat repertory = RepertoryImpl.getInstance().getCommunityDatInstance();
                repertory.getClassmateDyns(Long.parseLong(user.getUser()),1,5, new Repertory.GetDataListener<Dyns>() {
                    @Override
                    public void onSucceed(final Dyns data) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.upDateShowData2(data.getDyns());
                                view.hideLoading2();
                            }
                        });
                    }
                    @Override
                    public void Failed(final Repertory.FailedMsg msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.showMsg(msg.getMsg());
                                view.hideLoading1();
                            }
                        });

                    }
                });
            }
        }).start();
    }

}
