package com.sto.asportclient.data.util.bean;

public class UpdatePw {
    private String result;
    /**
     * 错误码
     */
    private int code;
    private String msg;
    private Long user;
    private String desc;
    private String nickname;

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Long getUser() {
        return user;
    }
    public void setUser(Long string) {
        this.user = string;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return "Result [result=" + result + ", msg=" + msg + ", user=" + user + ", desc=" + desc + ", nickname="
                + nickname + "]";
    }


}
