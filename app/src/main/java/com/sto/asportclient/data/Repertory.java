package com.sto.asportclient.data;


public interface Repertory {
    public static interface LoginListener{
        public void onSucceed(String msg);
        public void Failed(String msg);
    }
    public void login(String username, String password,LoginListener loginListener);
    public void logon();
    public void logout();
    public void testPage(LoginListener loginListener);
}
