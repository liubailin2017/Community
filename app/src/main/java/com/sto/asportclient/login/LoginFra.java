package com.sto.asportclient.login;

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
import android.widget.EditText;

import com.sto.asportclient.R;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;


public class LoginFra extends Fragment implements LoginContract.View {
    private EditText userView =null;
    private EditText pwView = null;
    private Button button = null;
    private LoginContract.Presenter presenter;
    private OnFragmentInteractionListener mListener;

    public LoginFra() {
        setPresenter(new LoginPresenter(this));
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = userView.getText().toString();
                final String password = pwView.getText().toString();
                presenter.login(username,password);
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void toActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void showErrMsg(String msg) {
        MyToast.getInstance(getActivity()).ShowToast(msg);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
