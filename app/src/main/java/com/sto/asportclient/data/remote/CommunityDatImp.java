package com.sto.asportclient.data.remote;

import com.alibaba.fastjson.JSON;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.util.bean.Comms;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommunityDatImp implements CommunityDat {


    private  OkHttpClient okHttpClient = null;
    @Override
    public void setOkhttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    @Override
    public void getComments(long dyn, final Repertory.getDataListener<Comms> listener) {

        FormBody.Builder form = new FormBody.Builder()
                .add("dynId",""+dyn);

        Request request = new Request.Builder().url(Config.URL_STR_GETCOMMS)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(form.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.Failed(new Repertory.FailedMsg(0,e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str_json = response.body().string();
                Comms comms = null;
                if(str_json!=null)
                    comms = JSON.parseObject(str_json,Comms.class);
                listener.onSucceed(comms);
            }
        });
    }
}
