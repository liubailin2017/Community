package com.sto.asportclient;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.login.LoginFra;
import com.sto.asportclient.util.ActivityUtils;
import com.sto.asportclient.util.MyToast;

public class MainActivity extends AppCompatActivity implements LoginFra.OnFragmentInteractionListener {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginFra  fra = (LoginFra) getSupportFragmentManager()
                .findFragmentById(R.id.fra_frame);
        if(fra == null) {
            fra = new LoginFra();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fra, R.id.fra_frame);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RepertoryImpl.getInstance().testPage(new Repertory.LoginListener() {
                    @Override
                    public void onSucceed(final String msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                MyToast.getInstance(MainActivity.this).ShowToast(msg);

                            }
                        });
                    }

                    @Override
                    public void Failed(final String msg) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                MyToast.getInstance(MainActivity.this).ShowToast(msg);
                            }
                        });
                    }
                });
            }
        }).start();

    }

}
