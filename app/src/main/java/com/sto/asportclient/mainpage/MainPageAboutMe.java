package com.sto.asportclient.mainpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.classmatedyns.ClassMateDynsActivity;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mainpage.adapter.DynAdapter;
import com.sto.asportclient.mydyns.MyDynsActivity;
import com.sto.asportclient.util.MyToast;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

public class MainPageAboutMe extends BaseActivity implements MainPageContract.View{

    private MainPageContract.Presenter presenter;

    private TextView tv_nickname;
    private User user;
    private DynAdapter adapter,adapter2;
    private Toolbar toolbar;
    private SwipeStack swipeStack1,swipeStack2;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public User curUser() {
        return  user;
    }
    public void init() {
        user = (User) getIntent().getExtras().get("user");
        tv_nickname = $$(R.id.tv_nickName);
        tv_nickname.setText(user.getNickname());
        toolbar = $$(R.id.main_toolbar);
        swipeStack1 = $$(R.id.swipeStack1);
        swipeStack2 = $$(R.id.swipeStack2);
        collapsingToolbarLayout = $$(R.id.main_page_colltoolbar);
        setSupportActionBar(toolbar);
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        adapter = new DynAdapter(MainPageAboutMe.this,dynsBean,presenter);
        adapter2 = new DynAdapter(MainPageAboutMe.this,dynsBean,presenter);
        swipeStack1.setAdapter(adapter);
        swipeStack2.setAdapter(adapter2);
        swipeStack1.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {
                swipeStack1.resetStack();
            }
        });
        swipeStack2.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {

            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {
                swipeStack2.resetStack();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTitleText2));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorTitleText));

        setPresenter(new MainPagePresenter(this,user));
        presenter.updateMydyn();
        presenter.updateClassmete();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_about_me);
        init();
    }


    @Override
    public void showLoading1() {

    }

    @Override
    public void showLoading2() {

    }

    @Override
    public void hideLoading1() {

    }

    @Override
    public void hideLoading2() {

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

    @Override
    public Context getContext() {
        return this;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myDyns_line:
                presenter.updateMydyn();
                toActivity(MyDynsActivity.class,user);
                break;
            case R.id.classmate_line:
                presenter.updateClassmete();
                toActivity(ClassMateDynsActivity.class,user);
                break;
        }
    }
}
