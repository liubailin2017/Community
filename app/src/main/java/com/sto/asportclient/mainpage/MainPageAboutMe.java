package com.sto.asportclient.mainpage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mainpage.adapter.DynAdapter;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

import java.util.ArrayList;

public class MainPageAboutMe extends BaseActivity implements MainPageContract.View{


    public <T extends View> T $$(int id) {
        return findViewById(id);
    }

    private MainPageContract.Presenter presenter;

    private TextView tv_nickname;
    private User user;
    private DynAdapter adapter,adapter2;
    private ListView listView;
    private  ListView listView2;
    private SwipeRefreshLayout swip1,swip2;

    public User curUser() {
        return  user;
    }
    public void init() {
        user = (User) getIntent().getExtras().get("user");
        tv_nickname = $$(R.id.tv_nickName);
        tv_nickname.setText(user.getNickname());
        listView = $$(R.id.tv_mydyns);
        listView2 = $$(R.id.tv_classmatedyns);
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        adapter = new DynAdapter(MainPageAboutMe.this,dynsBean,presenter);
        adapter2 = new DynAdapter(MainPageAboutMe.this,dynsBean,presenter);
        listView2.setAdapter(adapter2);
        listView.setAdapter(adapter);
        setPresenter((BasePresenter) new MainPagePresenter(this,user));
        presenter.updateMydyn();
        presenter.updateClassmete();

        swip1 = $$(R.id.swipe1);
        swip2 = $$(R.id.swipe2);

        swip1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateMydyn();
                swip1.setRefreshing(false);
            }
        });

        swip2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateClassmete();
                swip2.setRefreshing(false);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_about_me);
        init();
    }

    public void click(View view) {
    }
    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        WaitDialog dialog = WaitDialog.getInstance(this);
        dialog.setTitle("登录");
        dialog.setContent("请稍候...");
        dialog.show();
    }

    /**
     * 隐藏加载框
     */
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
    public void toActivity(Class<? extends Activity> activity, User user) {
        Intent intent = new Intent(this,activity);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    @Override
    public void upDateShowData(Dyns.DynsBean dyns) {
        adapter.setDynsBean(dyns);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void upDateShowData2(Dyns.DynsBean dyns) {
        adapter2.setDynsBean(dyns);
        adapter2.notifyDataSetChanged();
    }

    @Override
    public void addData(Dyns.DynsBean dyns) {
        adapter.addDynsBean(dyns);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addData2(Dyns.DynsBean dyns) {
        adapter2.addDynsBean(dyns);
        adapter2.notifyDataSetChanged();
    }


    @Override
    public void clearData() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearData2() {
        adapter2.clear();
        adapter2.notifyDataSetChanged();
    }


    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (MainPageContract.Presenter) presenter;
    }
}
