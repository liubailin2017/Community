package com.sto.asportclient;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

public class BaseActivity extends AppCompatActivity {

    public <T extends View> T $$(int id) {
        return findViewById(id);
    }

    @Override
    protected void onDestroy() {
        /**
         * 记住销毁这些单例对象
         */
        WaitDialog.getInstance(this).destroy();
        MyToast.getInstance(this).destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        super.onDestroy();
    }
}
