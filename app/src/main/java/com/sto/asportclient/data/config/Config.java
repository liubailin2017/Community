package com.sto.asportclient.data.config;

/**
 *  <a href="http://45.40.202.230:8080/asport">接口说明</a>
 */
public class Config {
    /**
     * 服务器地址
     */
    public static final String IP = "45.40.202.230";
    /**
     * 登录 url
     */
    public static final String LOGURL_STR = "http://"+IP+":8080/asport/login.do";
    /**
     * 获取评论接口
     */
    public static final String URL_STR_GETCOMMS = "http://"+IP+":8080/asport/getcomments.do";

    public  static  final String URL_STR_GETDYN = "http://"+IP+":8080/asport/myDyns.do";

    public  static final  String url_str_dynimg_base = "http://"+IP+":8080/asport/dynImg.png?id=";

    public static  final String URL_STR_GETCLASSMATEDYNS = "http://"+IP+":8080/asport/classMateDyn.do";

    public static final  String URL_STR_AddDyn = "http://"+IP+":8080/asport/addDyn.do";
    public static final  String URL_STR_DelDyn = "http://"+IP+":8080/asport/delDyn.do";

    public static final String URL_STR_AddComment = "http://"+IP+":8080/asport/addcommnet.do";

    public static final String URL_STR_UpdatePw = "http://"+IP+":8080/asport/updatePw.do";
    public static class ErrCode {
        public static int NETREFUSE = 0; //网络没连通
        public static int SERVICESERR = 1; //服务出错
    }
}
