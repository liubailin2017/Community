package com.sto.asportclient.util;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
    private static MyToast myToast  =null;
    private Context context = null;
    Toast toast;

    private  MyToast (Context context) {
        this.context = context;
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public static  MyToast getInstance(Context context) {

        if(myToast == null) {
            synchronized (MyToast.class) {
                if(myToast == null)
                    myToast = new MyToast(context);
            }
        }
        return  myToast;
    }

    public void ShowToast(String msg) {
        toast.setText(msg);
        toast.show();
    }

    public void destroy() {
        myToast = null;
    }
}
