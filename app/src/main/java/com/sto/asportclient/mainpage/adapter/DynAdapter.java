package com.sto.asportclient.mainpage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sto.asportclient.R;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.mainpage.MainPageContract.Presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DynAdapter extends BaseAdapter{
    private Presenter presenter;
    private Context mContext;
    private Dyns.DynsBean list;

    public DynAdapter(Context mContext,Dyns.DynsBean list,Presenter presenter) {
        this.list = list;
        this.mContext = mContext;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return list.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView = View.inflate(mContext, R.layout.mainpage_item,null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.img = (ImageView) convertView.findViewById(R.id.tv_DynImg);
            holder.footer = convertView.findViewById(R.id.tv_footer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Dyns.DynsBean.DynBean item = list.getList().get(position);
        holder.title.setText(item.getTitle());
        String content = item.getContent();
        if(content.length() > 300) {
            content = content.substring(0,300) + " ·····";
        }
        holder.content.setText(content);
        holder.footer.setText(new Date(item.getTime()).toString()+ "by " + item.getNickName());
        Glide.with(mContext)
                .load(Config.url_str_dynimg_base+item.getImgId())
                .into(holder.img);
        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView content;
        TextView footer;
        ImageView img;
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
