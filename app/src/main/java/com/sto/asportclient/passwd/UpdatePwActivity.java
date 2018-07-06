package com.sto.asportclient.passwd;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.MainActivity;
import com.sto.asportclient.R;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

public class UpdatePwActivity extends BaseActivity implements UpdatePwContract.View,View.OnClickListener{
    private Toolbar toolbar;
    private Button btn_updatePw;
    private EditText editpw1;
    private EditText editpw2;
    private EditText editpoldpw;
    private UpdatePwContract.Presenter presenter;
    void initView(){
        btn_updatePw = $$(R.id.updatePwBtn);
        btn_updatePw.setOnClickListener(this);
        editpw1 = $$(R.id.updatePwEdit1);
        editpw2 = $$(R.id.updatePwEdit2);
        editpoldpw = $$(R.id.oldpwEdit);
        toolbar = $$(R.id.updatepwToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pw);
        initView();
        presenter = new UpdatePwPresenter();presenter.setView(this);
    }

    @Override
    public void showLoading() {
        WaitDialog dialog = WaitDialog.getInstance(this);
        dialog.setTitle("等待");
        dialog.setContent("正在处理...");
        dialog.show();
    }

    @Override
    public void hideLoading() {
        Dialog dialog = WaitDialog.getInstance(this);
        dialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        MyToast.getInstance(this).ShowToast(msg);
    }

    @Override
    public void toLoginAct() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (UpdatePwContract.Presenter) presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void updatePw(){
        String oldpw,pw1,pw2;
        pw1 = editpw1.getText().toString();
        pw2 = editpw2.getText().toString();
        oldpw = editpoldpw.getText().toString();
        presenter.updatePw(oldpw,pw1,pw2);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updatePwBtn :
                updatePw();
                break;
        }
    }
}
