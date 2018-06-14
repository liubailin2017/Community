package com.sto.asportclient.data.util.bean;

public class DelDynBean {

    private String result;
    private String delcount;
    private String user;
    private String nickname;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDelcount() {
        return delcount;
    }

    public void setDelcount(String delcount) {
        this.delcount = delcount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "DelDynBean{" +
                "result='" + result + '\'' +
                ", delcount='" + delcount + '\'' +
                ", user='" + user + '\'' +
                ", nickname='" + nickname + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
