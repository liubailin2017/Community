package com.sto.asportclient.adddyn;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.BaseView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.AddDynBean;
import com.sto.asportclient.util.MyToast;

import java.io.File;

public class AddDynPresenter implements AddDynContract.Presenter {
    private Context context;
    private AddDynContract.View view;
    private Handler handler = new Handler(Looper.getMainLooper());

    public AddDynPresenter(AddDynContract.View view, Context context) {
       this.context = context;
       setView(view);
    }

    @Override
    public void addDyn(final String title, final String content, final File img) {
        view.showLoading();
        if(img != null && img.length() > 4*1024*1024) {
           view.hideLoading();
            view.showMsg("图片太大");
        }else
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RepertoryImpl.getInstance().getCommunityDatInstance().pushDyn( title, content,img, new Repertory.GetDataListener<AddDynBean>() {
                        @Override
                        public void onSucceed(final AddDynBean data) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.hideLoading();
                                    if(data != null && data.getResult().equals("S")) {
                                        MyToast.getInstance(context).ShowToast("上传成功");
                                        view.finshAct();
                                    }else {
                                        MyToast.getInstance(context).ShowToast("上传失败");
                                    }
                                }
                            });
                        }

                        @Override
                        public void Failed(Repertory.FailedMsg msg) {
                            view.hideLoading();
                            MyToast.getInstance(context).ShowToast("上传失败");
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void setView(BaseView view) {
        this.view = (AddDynContract.View) view;
    }
}
