package com.sto.asportclient.classmatedyns.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sto.asportclient.R;
import com.sto.asportclient.classmatedyns.ClassmateDynsContract;
import com.sto.asportclient.comment.CommentActivity;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.util.bean.Dyns;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DynAdapter extends BaseAdapter{
    private ClassmateDynsContract.Presenter presenter;
    private Context mContext;
    private Dyns.DynsBean list;
    private ClassmateDynsContract.View view;
    public DynAdapter(Context mContext, Dyns.DynsBean list, ClassmateDynsContract.Presenter presenter,ClassmateDynsContract.View view) {
        this.list = list;
        this.mContext = mContext;
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public int getCount() {
        return list.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return list.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.dyn_item,null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.img = (ImageView) convertView.findViewById(R.id.tv_DynImg);
            holder.cardView =convertView.findViewById(R.id.item_cardview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Dyns.DynsBean.DynBean item = list.getList().get(position);
        holder.title.setText(item.getTitle());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String content = item.getContent();
        if(content.length() > 300) {
            content = content.substring(0,300) + " ·····";
        }
        holder.content.setText(content+"\n"
                +new Date(item.getTime()).toString() + "  by "+item.getNickName());

        Glide.with(mContext)
                .load(Config.url_str_dynimg_base+item.getImgId())
//                .error(R.drawable.noimg)
                .into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("dynbean",item);
                view.toActivity(CommentActivity.class,presenter.getUser(),map);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView content;
        ImageView img;
        CardView cardView;
    }

    public void setDynsBean(Dyns.DynsBean list){
        this.list = list;
    }

    public void addDynsBean(Dyns.DynsBean list){
        for(Dyns.DynsBean.DynBean dynBean : list.getList())
         this.list.getList().add(dynBean);
    }

    public void clear() {
        Dyns.DynsBean dynsBean = new Dyns.DynsBean(0,0,0,0,0,0,0,0,new ArrayList<Dyns.DynsBean.DynBean>());
        setDynsBean(dynsBean);
    }
}
