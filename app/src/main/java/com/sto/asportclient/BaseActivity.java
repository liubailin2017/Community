package com.sto.asportclient;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

public class                          BaseActivity extends AppCompatActivity {

    public <T extends View> T $$(int id) {
        return findViewById(id);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
