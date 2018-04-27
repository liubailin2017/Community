package com.sto.asportclient.data.config;

public class Config {
    /**
     * 登录 url
     */
    public static final String LOGURL_STR = "http://192.168.1.103:8080/HELLO/login";
    public static final String SUCCESSFLAG = "成功";
    /**
     * 用于测试的url
     * 当登录过后，用来测试能否访问对应的资源
     */
    public static final String TestURL_STR = "http://192.168.1.103:8080/HELLO/users/explorer.html?path=D:/DataUpload";
}
