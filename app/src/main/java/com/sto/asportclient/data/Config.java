package com.sto.asportclient.data;

/**
 * app的设置，比如是否自动登录等
 */
public interface Config {

    public class  User {
        public User(String user, String pwd) {
            this.user = user;
            this.pwd = pwd;
        }

        public User() {
        }

        public String user;
        public String pwd;
    }
    public void savaUserAndPwd(User user);
    public User getUserAndPwd();
    public void clearUserAndPwd();
    public void setIsAutoLogin(boolean isAutoLogin);
    public Boolean getIsAutoLogin();
    public void setIsRememberUserPwd(boolean isRemember);
    public boolean getIsRememberUserPwd();


}
