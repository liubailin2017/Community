package com.sto.asportclient.comment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sto.asportclient.R;
import com.sto.asportclient.comment.CommentContract;
import com.sto.asportclient.data.util.bean.Comms;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<Comms.CommsBean> comms;
    private Context context;
    CommentContract.Presenter presenter;
    CommentContract.View view;
    public CommentAdapter (ArrayList<Comms.CommsBean> comms,CommentContract.View view, Context context, CommentContract.Presenter presenter) {
        this.comms = comms;
        this.context = context;
        this.presenter = presenter;
        this.view =view;
    }

    @Override
    public int getCount() {
        return comms.size();
    }

    @Override
    public Object getItem(int position) {
        return comms.get(position);
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
            convertView = View.inflate(context, R.layout.comment_item,null);
            holder.content = (TextView) convertView.findViewById(R.id.comment_content_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Comms.CommsBean item = comms.get(position);

        if(item.getCom_commnet_id() == -1) {
            holder.content.setTextColor(context.getResources().getColor(R.color.colorTitleText2));
            holder.content.setText(item.getStu_nickName()+" : " +item.getComment().getContent());
        }
        else {
            holder.content.setTextColor(context.getResources().getColor(R.color.colorTitleText));
            String t = "|-";
            for(int i = 0; i< item.level;i++){
                t= "| " + t;
            }
            holder.content.setText(t+item.getStu_nickName() + " 回复 " + item.getForComms_nickName() + ":" + item.getComment().getContent());
        }

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                presenter.addComments(view.getDynBean().getDynId(),"测试评论一下",item.getComment().getComment_id());
                view.showCommentEditor(item.getComment().getComment_id(),"回复"+item.getStu_nickName());
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView content;
    }

    public void addAll(ArrayList<Comms.CommsBean> comms) {
        comms.addAll(comms);
    }

    public void addOne(Comms.CommsBean comm) {
        comms.add(comm);
    }
    public void clear() {
        comms.clear();
    }

}
