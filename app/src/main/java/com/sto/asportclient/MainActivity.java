package com.sto.asportclient;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.amap.api.maps.MapView;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.login.LoginFra;
import com.sto.asportclient.util.ActivityUtils;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

import java.util.ArrayList;
import java.util.Map;

/**
 * 用于测试的Activity
 */
public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    MapView mMapView = null;

    private void initView() {
        mMapView = (MapView) findViewById(R.id.map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mMapView.onCreate(savedInstanceState);
        LoginFra  fra = (LoginFra) getSupportFragmentManager()
                .findFragmentById(R.id.fra_frame);
        if(fra == null) {
            fra = new LoginFra();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fra, R.id.fra_frame);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        /**
         * 记住销毁这些单例对象
         */
        WaitDialog.getInstance(this).destroy();
        MyToast.getInstance(this).destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        super.onDestroy();
    }

    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                RepertoryImpl.getInstance().getCommunityDatInstance().getComments(7, new Repertory.getDataListener<Comms>() {
                    @Override
                    public void onSucceed(final Comms data) {
                        Log.i("++++++++++++","_+++++++++++++");
                        ArrayList<Comms.CommsBean> arrayList = data.removeCommsForDyns();
                        for(int i = 0; i< arrayList.size();i++) {
                            Log.i("------","+"+arrayList.get(i).getStu_nickName()+"："+arrayList.get(i).getComment().getContent());

                            data.clear();
                            ArrayList<Comms.CommsBean> arrayList2 = data.removeCommsForComms(arrayList.get(i));
                            for(int j =0;j< arrayList2.size();j++) {
                                Log.i("-------",arrayList2.get(j).getStu_nickName()+arrayList2.get(j).getComment().getContent());
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
