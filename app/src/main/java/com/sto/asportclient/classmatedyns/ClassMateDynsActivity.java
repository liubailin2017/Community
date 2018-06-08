package com.sto.asportclient.classmatedyns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.classmatedyns.adapter.DynAdapter;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.RefreshLayout;
import com.sto.asportclient.util.MyToast;

import java.util.ArrayList;

public class ClassMateDynsActivity extends BaseActivity implements MyDynsContract.View {
    private  User user;
    private RefreshLayout swip;
    private ListView listView;
    private MyDynsContract.Presenter presenter;
    private DynAdapter adapter;
    private  void init() {
        swip = $$(R.id.classmateDyns_swipe);
        listView = $$(R.id.tv_classmatedyn_listview);
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        adapter = new DynAdapter(this,dynsBean,presenter);
        listView.setAdapter(adapter);
        presenter = new MyDynsPresenter(this,user);
        presenter.updateMydyn();
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateMydyn();
            }
        });
        swip.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                   presenter.loadNext();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classmate_dyns);
        user = (User) getIntent().getExtras().get("user");
        init();
    }

    @Override
    public void showRefreshing() {
        swip.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        swip.setRefreshing(false);
    }

    @Override
    public void hideLoadingBttom() {
        swip.setLoading(false);
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
}
