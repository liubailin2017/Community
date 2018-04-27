package com.sto.asportclient.data.remote;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.util.MyToast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RepertoryImpl implements Repertory{

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
        Request request = new Request.Builder().url(Config.LOGURL_STR)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie" ,cookies)
                .post(form.build())
                .build();

            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {

                            callback.Failed(e.getMessage());


                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String res = response.body().string();
                    String tmp =  response.header("Set-Cookie");
                    if(tmp != null)
                        cookies = tmp;
                    if(res.equals(Config.SUCCESSFLAG)) {

                                callback.onSucceed(null);


                    }
                    else{

                                callback.Failed(res);

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

    public void testPage(final LoginListener loginListener) {
        Request request = new Request.Builder().url(Config.TestURL_STR)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie" ,cookies)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            final  String res = response.body().string();
                   loginListener.onSucceed(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
