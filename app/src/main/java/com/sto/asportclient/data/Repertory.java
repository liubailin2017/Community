package com.sto.asportclient.data;

import com.sto.asportclient.data.util.bean.User;

/**
 * 数据仓库
 */
public interface Repertory {
    public static interface LoginListener{
        public void onSucceed(User user);
        public void Failed(String msg);
    }

    /**
     * 失败时返回的消息
     */
    public class  FailedMsg {
        private int code;
        private String msg;

        public FailedMsg() {
        }

        public FailedMsg(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 用来获取数据的
     * @param <T>
     */
    public static interface GetDataListener<T> {
        public void onSucceed(T data);
        public void Failed(FailedMsg msg);
    }

    public void login(String username, String password,LoginListener loginListener);
    public void logon();
    public void logout();

    /**
     * 用来获取社区数据接口
     * @return
     */
    public  CommunityDat getCommunityDatInstance();

}
