package com.sto.asportclient.data;

import android.support.annotation.NonNull;

import com.sto.asportclient.data.util.bean.AddBean;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.data.util.bean.DelDynBean;
import com.sto.asportclient.data.util.bean.Dyns;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * 社区数据 ， 请用 {@link Repertory} getCommunityDatInstance() 得实例。
 */
public interface CommunityDat {

    public void setOkhttpClient(OkHttpClient okHttpClient);

    public void getComments(long dyn,Repertory.GetDataListener<Comms> listener);
    public void addComments(long dynId,String content,Long com_Comment_id,Repertory.GetDataListener<AddBean> listener);

    public void getDyns(Long stu_nmb, Repertory.GetDataListener<Dyns> listener);

    public void getDynsSelect(Long stu_nmb, Integer pageNo, Integer pageSize,@NonNull Repertory.GetDataListener<Dyns> listener);

    public void getClassmateDyns(Long stu_nmb, Integer pageNo, final Integer pageSize,@NonNull Repertory.GetDataListener<Dyns> listener);

    public void pushDyn(String title, String content, File img,@NonNull Repertory.GetDataListener<AddBean> listener);

    public void deleteDyn(Long dynId, Repertory.GetDataListener<DelDynBean> listener);
}
