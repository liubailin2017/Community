package com.sto.asportclient.data.remote;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sto.asportclient.data.CommunityDat;
import com.sto.asportclient.data.Repertory;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.util.bean.AddDynBean;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.data.util.bean.DelDynBean;
import com.sto.asportclient.data.util.bean.Dyns;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunityDatImp implements CommunityDat {


    private  OkHttpClient okHttpClient = null;
    @Override
    public void setOkhttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    @Override
    public void getComments(long dyn, final Repertory.GetDataListener<Comms> listener) {

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

    /**
     * 这个主要用来兼容以前的代码
     * @param stu_nmb
     * @param listener
     */
    @Override
    public void getDyns(Long stu_nmb, final Repertory.GetDataListener<Dyns> listener) {
        getDynsSelect(stu_nmb,null,null,listener);
    }

    /**
     * 获得指定人的动态
     * @param stu_nmb 学号 如果为null，则为当前登录用户
     * @param pageNo
     * @param pageSize
     * @param listener
     */
    @Override
    public void getDynsSelect(Long stu_nmb, Integer pageNo, final Integer pageSize, final Repertory.GetDataListener<Dyns> listener) {
        Request request;
        if(stu_nmb == null) {
            request = new Request.Builder().url(Config.URL_STR_GETDYN)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        } else {
            FormBody.Builder form = new FormBody.Builder()
                    .add("stu_nmb", "" + stu_nmb);
            if(pageNo != null)  form.add("pageNo",""+pageNo);
            if(pageSize != null) form.add("pageSize",""+pageSize);

            request = new Request.Builder().url(Config.URL_STR_GETDYN)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(form.build())
                    .build();
        }
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.Failed(new Repertory.FailedMsg(0,e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Dyns dyns = JSON.parseObject(response.body().string(),Dyns.class);
                    Log.i("reslut :","communityDatImp.getDyns "+ dyns.toString());
                    listener.onSucceed(dyns);
                }catch (Exception e) {
                    e.printStackTrace();
                    listener.Failed(new Repertory.FailedMsg(0,e.getMessage()));
                }
            }
        });
    }

    /**
     * 同学的动态
     * @param stu_nmb stu_nmb学号学生的同学的动态
     * @param pageNo
     * @param pageSize
     * @param listener
     */
    @Override
    public void getClassmateDyns(Long stu_nmb, Integer pageNo, final Integer pageSize, final Repertory.GetDataListener<Dyns> listener) {
        Request request;
        if(stu_nmb == null) {
            request = new Request.Builder().url(Config.URL_STR_GETDYN)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        } else {
            FormBody.Builder form = new FormBody.Builder()
                    .add("stu_nmb", "" + stu_nmb);
            if(pageNo != null)  form.add("pageNo",""+pageNo);
            if(pageSize != null) form.add("pageSize",""+pageSize);

            request = new Request.Builder().url(Config.URL_STR_GETCLASSMATEDYNS)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(form.build())
                    .build();
        }
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.Failed(new Repertory.FailedMsg(Config.ErrCode.NETREFUSE,e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Dyns dyns = JSON.parseObject(response.body().string(),Dyns.class);
                    Log.i("reslut :","communityDatImp.getDyns "+ dyns.toString());
                    listener.onSucceed(dyns);
                }catch (Exception e) {
                    e.printStackTrace();
                    listener.Failed(new Repertory.FailedMsg(Config.ErrCode.SERVICESERR,e.getMessage()));
                }
            }
        });
    }

    @Override
    public void pushDyn(String title, String content, File img, final Repertory.GetDataListener<AddDynBean> listener) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("content",content)
                .addFormDataPart("title",title);

        if(img != null) {
            RequestBody img1 = RequestBody.create(MediaType.parse("image/png"),img);
            builder.addFormDataPart("img1","img",img1);
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(Config.URL_STR_AddDyn)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.Failed(new Repertory.FailedMsg(Config.ErrCode.NETREFUSE,e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                try {
                    AddDynBean resObj = JSON.parseObject(res,AddDynBean.class);
                    listener.onSucceed(resObj);
                }catch (Exception e) {
                    e.printStackTrace();
                    listener.Failed(new Repertory.FailedMsg(Config.ErrCode.SERVICESERR,e.getMessage()));
                }
            }
        });
    }

    @Override
    public void deleteDyn(Long dynId, final Repertory.GetDataListener<DelDynBean> listener) {
        FormBody formBody = new FormBody.Builder().add("dynId",""+dynId).build();
        Request request = new Request.Builder()
                .url(Config.URL_STR_DelDyn)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.Failed(new Repertory.FailedMsg(Config.ErrCode.NETREFUSE,e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                try {
                    DelDynBean resObj = JSON.parseObject(res,DelDynBean.class);
                    listener.onSucceed(resObj);
                }catch (Exception e) {
                    e.printStackTrace();
                    listener.Failed(new Repertory.FailedMsg(Config.ErrCode.SERVICESERR,e.getMessage()));
                }
            }
        });
    }


}
