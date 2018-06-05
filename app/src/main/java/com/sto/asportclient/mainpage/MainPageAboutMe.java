package com.sto.asportclient.mainpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sto.asportclient.R;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.remote.RepertoryImpl;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.data.util.bean.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainPageAboutMe extends AppCompatActivity {
    TextView tv_nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_about_me);
        tv_nickname = findViewById(R.id.tv_nickName);
        User user = (User) getIntent().getExtras().get("user");
        tv_nickname.setText(user.getNickname());
    }
//
//    public void click(View view) {
//        Log.i("++++++++++++","+++++++++++");
//    }


    public void click(View view) {

    }

}
