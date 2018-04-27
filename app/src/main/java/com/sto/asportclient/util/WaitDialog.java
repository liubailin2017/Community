package com.sto.asportclient.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import com.sto.asportclient.R;

public class WaitDialog extends Dialog{
    private static WaitDialog waitDialog;

    public static  WaitDialog getInstance(Context context){
        if(waitDialog == null) {
            synchronized (WaitDialog.class) {
                if(waitDialog == null) {
                    waitDialog = new WaitDialog(context);
                }
            }
        }
        return  waitDialog;
    }

    TextView textView = null;
    TextView titleView = null;
    public WaitDialog(@NonNull Context context) {
        super(context);
        setCanceledOnTouchOutside(false);//按对话框以外的地方不起作用，按返回键可以取消对话框
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.wait_dialog);
        textView = findViewById(R.id.content_view);
        titleView = findViewById(R.id.title_view);
    }
    public void setTitle(String title) {
        titleView.setText(title);
    }
    public void setContent(String content) {
        textView.setText(content);
    }
}
