package com.sto.asportclient.passwd;

import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.UpdatePw;

public class UpdatePwPresenter implements UpdatePwContract.Presenter {
    private CommunityDat communityDat = RepertoryImpl.getInstance().getCommunityDatInstance();
    private Repertory repertory= RepertoryImpl.getInstance();
    private UpdatePwContract.View view;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void setView(BaseView view) {
        this.view = (UpdatePwContract.View) view;
    }

    @Override
    public void updatePw(String oldpw, String newPw, String reNewPw) {

        if(newPw.equals(reNewPw)) {
            repertory.updatePw(oldpw, newPw, new Repertory.GetDataListener<UpdatePw>() {
                @Override
                public void onSucceed(final UpdatePw data) {
                    if ("S".equals(data.getResult())) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.hideLoading();
                                view.showMsg("修改成功");
                                view.toLoginAct();
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                view.hideLoading();
                                view.showMsg("msg:" + data.getMsg());
                            }
                        });
                    }
                }

                @Override
                public void Failed(final Repertory.FailedMsg msg) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            view.hideLoading();
                            view.showMsg("failed:" + msg.getMsg());
                        }
                    });
                }
            });
        }
        else {
            view.showMsg("再次密码不一致，请重新输入");
        }
    }
}
