package com.sto.asportclient.data;

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

}
