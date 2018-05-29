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
