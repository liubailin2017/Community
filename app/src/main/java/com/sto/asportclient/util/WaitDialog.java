package com.sto.asportclient.util;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.sto.asportclient.R;

public class WaitDialog extends Dialog{
    private Context context;
    private static WaitDialog waitDialog;
    private Runnable onPressBack;

    public void setOnPressBack(Runnable onPressBack) {
        this.onPressBack = onPressBack;
    }

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
    public WaitDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        setCanceledOnTouchOutside(false);//按对话框以外的地方不起作用，按返回键可以取消对话框
        getWindow().setGravity(Gravity.CENTER);
        setContentView(R.layout.wait_dialog);
        textView = findViewById(R.id.content_view);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height=480;
        lp.width=640;
        getWindow().setAttributes(lp);
    }

    public void setContent(String content) {
        textView.setText(content);
    }

    @Override
    public void onBackPressed() {
        if(onPressBack != null) {
            onPressBack.run();
            super.onBackPressed();
        }
    }

    /**
     * 销毁WaitDialog
     * 总结：
     *
     */
    public void destroy() {
        waitDialog = null;
    }
}
