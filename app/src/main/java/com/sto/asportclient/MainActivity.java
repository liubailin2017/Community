package com.sto.asportclient;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.login.LoginFra;
import com.sto.asportclient.util.ActivityUtils;

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





}
