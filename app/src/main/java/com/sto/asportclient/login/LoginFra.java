package com.sto.asportclient.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;


public class LoginFra extends Fragment implements LoginContract.View {
    private EditText userView =null;
    private EditText pwView = null;
    private Button button = null;
    private CheckBox checkBox_remember;
    private CheckBox checkBox_auto;
    private LoginContract.Presenter presenter;
    private TextView logonBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new LoginPresenter(this));  //这里使presenter和view产生关联
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pwView = getView().findViewById(R.id.pwView);
        userView = getView().findViewById(R.id.userView);
        button = getView().findViewById(R.id.loginBtn);
        checkBox_remember = getView().findViewById(R.id.checkbox_remember_pwd);
        checkBox_auto = getView().findViewById(R.id.checkBox_atuologin);
        logonBtn = getView().findViewById(R.id.logonBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = userView.getText().toString();
                final String password = pwView.getText().toString();
                presenter.login(username,password);
            }
        });
        logonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://45.40.202.230:8080/asport/logon.jsp");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        presenter.recovery();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (LoginContract.Presenter) presenter;
    }
    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        WaitDialog dialog = WaitDialog.getInstance(getActivity());
        dialog.setTitle("登录");
        dialog.setContent("请稍候...");
        dialog.show();
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoading() {
        Dialog dialog = WaitDialog.getInstance(getActivity());
        dialog.dismiss();
    }

    @Override
    public void setUser_pwd(String user, String passwd) {
        userView.setText(user);
        pwView.setText(passwd);
    }

    @Override
    public void setIsAutoLogin(boolean isAutoLogin) {
        checkBox_auto.setChecked(isAutoLogin);
    }

    @Override
    public void setIsRemenber(boolean isRemenber) {
            checkBox_remember.setChecked(isRemenber);
    }

    @Override
    public boolean isCheckRemember() {
        return checkBox_remember.isChecked();
    }

    @Override
    public boolean isAutoLogin() {
        return checkBox_auto.isChecked();
    }

    @Override
    public void showMsg(String msg) {
        MyToast.getInstance(getActivity()).ShowToast(msg);
    }

    @Override
    public void toActivity(Class<? extends Activity> activity, User user) {
        Intent intent = new Intent(getActivity(),activity);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
// fragment 已经实现了 getContext()
//    @Override
//    public Context getContext(){
//        return super.getContext();
//    }

}
