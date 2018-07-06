package com.sto.asportclient.data.util.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * result : S
     * user : 163796
     */
    private int head;
    private String result;
    private String user;
    private String nickname;
    private String msg;

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "result='" + result + '\'' +
                ", user='" + user + '\'' +
                ", nickname='" + nickname + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
