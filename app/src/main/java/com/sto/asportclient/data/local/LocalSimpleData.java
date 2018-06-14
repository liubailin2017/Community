package com.sto.asportclient.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.sto.asportclient.data.Config;

/**
 * 这个主要用来记住一些app 的设置，并不是用来缓存数据的。
 */

public class LocalSimpleData implements Config{
    private static LocalSimpleData staticInstance;
    private SharedPreferences sharedPreferences;
    private Context context;

    private  LocalSimpleData(Context context) {
        this.context = context;
        sharedPreferences =  context.getSharedPreferences("config",Context.MODE_PRIVATE);
    }

    public static LocalSimpleData getInstance(Context context) {
        if(staticInstance == null) {
            synchronized (LocalSimpleData.class) {
                if(staticInstance == null) {
                    staticInstance = new LocalSimpleData(context);
                }
            }
        }
        return staticInstance;
    }

    @Override
    public void savaUserAndPwd(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",user.user);
        editor.putString("password",user.pwd);
        editor.commit();
    }

    public User getUserAndPwd() {
        User user = new User();
        user.user = sharedPreferences.getString("username","-1");
        user.pwd = sharedPreferences.getString("password","-1");
        if("-1".equals(user.user) || "-1".equals(user.pwd))
            return null;
        else
            return user;
    }
}
