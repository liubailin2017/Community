package com.sto.asportclient.mydyns;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.adddyn.AddDynActivity;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mydyns.adapter.DynAdapter;
import com.sto.asportclient.util.MyToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class MyDynsActivity extends BaseActivity implements MyDynsContract.View {
    private  User user;
    private SmartRefreshLayout swip;
    private ListView listView;
    private MyDynsContract.Presenter presenter;
    private DynAdapter adapter;
    private TextView nicknameView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private  void init() {
        toolbar = $$(R.id.mydyns_toolbar);
        swip = $$(R.id.mydyns_refreshLayout);
        listView = $$(R.id.tv_mydyn_listview);
        nicknameView = $$(R.id.tv_nickName_mydyn);
        collapsingToolbarLayout = $$(R.id.mydyns_collToolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTitleText2));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorTitleText));
        collapsingToolbarLayout.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorTitleText2));

        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        presenter = new MyDynsPresenter(this,user);
        adapter = new DynAdapter(this,dynsBean,presenter,this);
        listView.setAdapter(adapter);
        listView.setNestedScrollingEnabled(true);
        presenter.updateMydyn();
        swip.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                presenter.loadNext();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.updateMydyn();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateMydyn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dyns);
        init();
    }

    @Override
    public void showRefreshing() {
        swip.autoRefresh();
    }

    @Override
    public void hideRefreshing() {
        swip.finishRefresh();
    }

    @Override
    public void hideLoadingBttom() {
        swip.finishLoadMore();
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
    public void toActivity(Class<? extends Activity> activity, User user, Map<String,Serializable> map) {
        Intent intent = new Intent(this,activity);
        intent.putExtra("user",user);
        for(String key :map.keySet()) {
            intent.putExtra(key,map.get(key));
        }
        startActivity(intent);
    }

    @Override
    public void upDateShowData(Dyns.DynsBean dyns) {
        adapter.setDynsBean(dyns);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addData(Dyns.DynsBean dyns) {
        adapter.addDynsBean(dyns);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUser(User user) {
        this.user = user;
        nicknameView.setText(user.getNickname());
    }

    @Override
    public void clearData() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (MyDynsContract.Presenter) presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDynBtn :
                toActivity(AddDynActivity.class,user);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
