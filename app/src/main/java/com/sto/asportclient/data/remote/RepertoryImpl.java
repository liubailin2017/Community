package com.sto.asportclient.data.remote;

import android.os.Handler;
import android.os.Looper;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.config.Config;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RepertoryImpl implements Repertory{
    private Handler handler = new Handler(Looper.getMainLooper());
    private static Repertory repertory = new RepertoryImpl();
    private OkHttpClient httpClient = new OkHttpClient();

    private String cookies = "";

    private RepertoryImpl() {}
    public static Repertory getInstance() {
       return repertory;
    }

    @Override
    public void login(String username, String password, final LoginListener callback) {
        FormBody.Builder form = new FormBody.Builder();
        form.add("client", "1");
        form.add("username",username);
        form.add("password",password);
        Request request = new Request.Builder().url(Config.SERURL_STR)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie" ,cookies)
                .post(form.build())
                .build();

            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.Failed(e.getMessage());
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String res = response.body().string();

                    if(res.equals(Config.SUCCESSFLAG)) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSucceed();
                            }
                        });
                        String tmp =  response.header("set-cookies");
                        if(tmp != null)
                            cookies = tmp;
                    }
                    else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.Failed(res);
                            }
                        });

                    }

                }
            });
    }

    @Override
    public void logon() {

    }

    @Override
    public void logout() {

    }
}
