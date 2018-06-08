package com.sto.asportclient.mainpage.util;

import android.os.Looper;

/**
 * 同步网络请求，解决异步请求时数据会错乱
 */
public class SynTask extends Thread {
    Looper myLooper;
    @Override
    public void run() {
        Looper.prepare();//必须在Looper.myLooper()之前
        myLooper = Looper.myLooper();
        Looper.loop();
    }


    public Looper getMyLooper() {
        /**
         * 等待
         */
        while(myLooper == null) {
            ;
        }
        return  myLooper;
    }
}
