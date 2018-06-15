package com.sto.asportclient.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.sto.asportclient.data.Config;

/**
 * 这个主要用来记住一些app 的设置，并不是用来缓存数据的。
 */

public class LocalSimpleData implements Config{
    private static LocalSimpleData staticInstance;
    private SharedPreferences loginSharedPreferences; // 登录相关
    private Context context;

    private  LocalSimpleData(Context context) {
        this.context = context;
        loginSharedPreferences =  context.getSharedPreferences("config",Context.MODE_PRIVATE);
    }

    public static LocalSimpleData getInstance(Context context) {
        if (staticInstance == null) {
            synchronized (LocalSimpleData.class) {
                if (staticInstance == null) {
                    staticInstance = new LocalSimpleData(context);
                }
            }
        }
        return staticInstance;
    }

    @Override
    public void savaUserAndPwd(User user) {
        SharedPreferences.Editor editor = loginSharedPreferences.edit();
        editor.putString("username",user.user);
        editor.putString("password",user.pwd);
        editor.commit();
    }

    public User getUserAndPwd() {
        User user = new User();
        user.user = loginSharedPreferences.getString("username","-1");
        user.pwd = loginSharedPreferences.getString("password","-1");
        if("-1".equals(user.user) || "-1".equals(user.pwd))
            return null;
        else
            return user;
    }

    @Override
    public void clearUserAndPwd() {
        SharedPreferences.Editor editor = loginSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        SharedPreferences.Editor editor = loginSharedPreferences.edit();
        editor.putBoolean("isAutoLogin",isAutoLogin);
        editor.commit();
    }

    @Override
    public Boolean getIsAutoLogin() {
        return loginSharedPreferences.getBoolean("isAutoLogin",false);
    }

    @Override
    public void setIsRememberUserPwd(boolean isRemember) {
        SharedPreferences.Editor editor = loginSharedPreferences.edit();
        editor.putBoolean("isRememberUserPwd",isRemember);
        editor.commit();
    }

    @Override
    public boolean getIsRememberUserPwd() {
        return loginSharedPreferences.getBoolean("isRememberUserPwd",false);
    }
}
