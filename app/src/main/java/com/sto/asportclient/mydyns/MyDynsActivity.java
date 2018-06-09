package com.sto.asportclient.mydyns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.adddyn.AddDynActivity;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.mydyns.adapter.DynAdapter;
import com.sto.asportclient.util.MyToast;

import java.util.ArrayList;

public class MyDynsActivity extends BaseActivity implements MyDynsContract.View {
    private  User user;
    private SmartRefreshLayout swip;
    private ListView listView;
    private MyDynsContract.Presenter presenter;
    private DynAdapter adapter;
    private  void init() {
        swip = $$(R.id.mydyns_refreshLayout);
        listView = $$(R.id.tv_mydyn_listview);
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        adapter = new DynAdapter(this,dynsBean,presenter);
        listView.setAdapter(adapter);
        presenter = new MyDynsPresenter(this,user);

        presenter.updateMydyn();
        swip.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                presenter.loadNext();
            }

            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                presenter.updateMydyn();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dyns);
        user = (User) getIntent().getExtras().get("user");
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
        swip.finishLoadmore();
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
    public void addData(Dyns.DynsBean dyns) {
        adapter.addDynsBean(dyns);
        adapter.notifyDataSetChanged();
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDynBtn :
                toActivity(AddDynActivity.class,user);
                break;
        }
    }
}
