package com.sto.asportclient.data;

import android.content.Context;

import com.sto.asportclient.data.util.bean.UpdatePw;
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
     * 用来获取数据的
     * @param <T>
     */
    public static interface GetDataListener<T> {
        public void onSucceed(T data);
        public void Failed(FailedMsg msg);
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

        @Override
        public String toString() {
            return "FailedMsg{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }


    public void login(String username, String password, LoginListener loginListener);
    public User getCurUser();
    public void updatePw(String oldPw, String newPw, Repertory.GetDataListener<UpdatePw> listener);
    public void logon();
    public void logout();

    /**
     * 用来获取社区数据接口
     * @return
     */
    public  CommunityDat getCommunityDatInstance();
    public Config getConfig(Context context);
}
