package com.sto.asportclient;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.MapView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.login.LoginFra;
import com.sto.asportclient.util.ActivityUtils;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

import java.util.ArrayList;

/**
 * 用于测试的Activity
 */
public class MainActivity extends BaseActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginFra  fra = (LoginFra) getSupportFragmentManager().findFragmentById(R.id.fra_frame);
        if(fra == null) {
            fra = new LoginFra();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fra, R.id.fra_frame);
        }
    }



    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RepertoryImpl.getInstance().getCommunityDatInstance().getComments(7, new Repertory.GetDataListener<Comms>() {
                    @Override
                    public void onSucceed(final Comms data) {
                        Log.i("data",data.toString());
                        ArrayList<Comms.CommsBean> arrayList = data.removeCommsForDyns();
                        for(int i = 0; i< arrayList.size();i++) {
                            Log.i(arrayList.get(i).getStu_nickName()+"回复"+arrayList.get(i).getForComms_nickName(),"+"+arrayList.get(i).getComment().getContent());
                            data.removeCommsForComms(arrayList.get(i));
                            ArrayList<Comms.CommsBean> arrayList2 = data.back();
                            for(int j =0;j< arrayList2.size();j++) {
                                Log.i(arrayList2.get(j).getStu_nickName()+"回复"+arrayList2.get(j).getForComms_nickName(),arrayList2.get(j).getComment().getContent());
                            }
                        }
                    }
                    @Override
                    public void Failed(Repertory.FailedMsg msg) {

                    }
                });
            }
        }).start();
    }

}
