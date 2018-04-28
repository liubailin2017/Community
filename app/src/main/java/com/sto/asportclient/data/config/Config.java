package com.sto.asportclient.data.config;

public class Config {
    public static final String IP = "10.130.23.21";
    /**
     * 登录 url
     */
    public static final String LOGURL_STR = "http://"+IP+":8080/HELLO/login";
    public static final String SUCCESSFLAG = "成功";
    /**
     * 用于测试的url
     * 当登录过后，用来测试能否访问对应的资源
     */
    public static final String TestURL_STR = "http://"+IP+":8080/HELLO/users/explorer.html?path=D:/DataUpload";
}
