package com.sto.asportclient.data.remote;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.local.LocalSimpleData;
import com.sto.asportclient.data.util.bean.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RepertoryImpl implements Repertory{

    private List<Cookie> cookies = new ArrayList<>();
    private static Repertory repertory = new RepertoryImpl();
    private CommunityDat communityDatInstance = null;
    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                            @Override
                            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                                for (Cookie cookie : cookies) {
                                    RepertoryImpl.this.cookies.add(cookie);
                                }
                            }

                            @Override
                            public List<Cookie> loadForRequest(HttpUrl url) {
                                return RepertoryImpl.this.cookies;
                            }
                        })
            .build();

    /**
     * 返回okhttpclient实例。
     * @return
     */
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    private RepertoryImpl() {}

    public static Repertory getInstance() {
       return repertory;
    }



    @Override
    public void login(String username, String password, final LoginListener callback) {
        cookies.clear();
        FormBody.Builder form = new FormBody.Builder()
                .add("stu_nmb",username)
                .add("passwd",password);
        Request request = new Request.Builder().url(Config.LOGURL_STR)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(form.build())
                .build();
             Call call = httpClient.newCall(request);
             call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                            callback.Failed(e.getMessage());
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String res = response.body().string();
                    User user;

                    try {
                        user = JSON.parseObject(res, User.class);
                    }catch (Exception e) {
                        user = new User();
                        user.setResult("s");
                        user.setMsg("服务器异常");
                    }

                    if(user.getResult().equals("S")) {
                        callback.onSucceed(user);
                    }
                    else{
                        callback.Failed(user.getMsg());
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

    @Override
    public CommunityDat getCommunityDatInstance() {
        if(communityDatInstance == null)
        synchronized (this) {
            if (communityDatInstance == null) {
                communityDatInstance = new CommunityDatImp();
            }
        }
        communityDatInstance.setOkhttpClient(httpClient);
        return communityDatInstance;
    }

    @Override
    public com.sto.asportclient.data.Config getConfig(Context context) {
        return LocalSimpleData.getInstance(context);
    }
}
