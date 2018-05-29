package com.sto.asportclient.data;

import com.sto.asportclient.data.util.bean.Comms;

import okhttp3.OkHttpClient;

/**
 * 社区数据 ， 请用 {@link Repertory} getCommunityDatInstance() 得实例。
 */
public interface CommunityDat {

    public void setOkhttpClient(OkHttpClient okHttpClient);

    public void getComments(long dyn,Repertory.getDataListener<Comms> listener);

}
