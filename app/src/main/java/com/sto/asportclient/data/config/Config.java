package com.sto.asportclient.data.config;

import com.alibaba.fastjson.JSON;
import com.sto.asportclient.data.util.bean.User;

public class Config {
    /**
     * 服务器地址
     */
    public static final String IP = "45.40.202.230";
    /**
     * 登录 url
     */
    public static final String LOGURL_STR = "http://"+IP+":8080/asport/login.do";
    public static final String URL_STR_GETCOMMS = "http://"+IP+":8080/asport/getcomments.do";

    public  static  final String URL_STR_GETDYN = "http://"+IP+":8080/asport/myDyns.do";
    public  static final  String url_str_dynimg_base = "http://"+IP+":8080/asport/dynImg.png?id=";
    /**
     * 用于测试的url
     * 当登录过后，用来测试能否访问对应的资源
     */
    public static final String TestURL_STR = "http://"+IP+":8080/asport/classMateDyn.do";

    public static  final String URL_STR_GETCLASSMATEDYNS = "http://"+IP+":8080/asport/classMateDyn.do";
}
