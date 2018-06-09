package com.sto.asportclient.classmatedyns;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.classmatedyns.adapter.DynAdapter;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.MyToast;

import java.util.ArrayList;

public class ClassMateDynsActivity extends BaseActivity implements ClassmateDynsContract.View {
    private  User user;
    private SmartRefreshLayout swip;
    private ListView listView;
    private ClassmateDynsContract.Presenter presenter;
    private DynAdapter adapter;
    private  void init() {
        swip = $$(R.id.classmate_refreshLayout);
        listView = $$(R.id.tv_classmatedyn_listview);
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        adapter = new DynAdapter(this,dynsBean,presenter);
        listView.setAdapter(adapter);
        presenter = new ClassmateDynsPresenter(this,user);
        presenter.updateMydyn();

        swip.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                presenter.loadNext();
                Log.i("onlocad","++++++++++++++");
            }

            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                presenter.updateMydyn();
                Log.i("onrefresh","+++++++++++++++++++++++");
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyToast.getInstance(ClassMateDynsActivity.this).ShowToast(position+"");
//            }
//        });
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
        this.presenter = (ClassmateDynsContract.Presenter) presenter;
    }
}
