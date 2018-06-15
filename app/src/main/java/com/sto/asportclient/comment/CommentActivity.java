package com.sto.asportclient.comment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.comment.adapter.CommentAdapter;
import com.sto.asportclient.data.config.Config;
import com.sto.asportclient.data.util.bean.Comms;
import com.sto.asportclient.data.util.bean.Dyns;
import com.sto.asportclient.data.util.bean.User;
import com.sto.asportclient.util.MyToast;

import java.util.ArrayList;

public class CommentActivity extends BaseActivity implements CommentContract.View{
    private CommentPresenter presenter;
    private TextView title;
    private TextView content;
    private ImageView img;
    private EditText editText;
    private ListView listView;
    CommentAdapter adapter;
    LinearLayout linearLayout;
    Dyns.DynsBean.DynBean dynBean;
    private  Long com_commit_id;
    public Dyns.DynsBean.DynBean getDynBean() {
        return dynBean;
    }

    @Override
    public void showCommentEditor(Long comm_id,String msg) {
        com_commit_id = comm_id;
        linearLayout.setVisibility(View.VISIBLE);
        editText.setText(null);
        editText.setHint(msg);
    }

    @Override
    public void hideCommentEditor() {
        linearLayout.setVisibility(View.INVISIBLE);
    }

    private void initView() {
        title = $$(R.id.comment_title);
        content = $$(R.id.comment_content);
        img = $$(R.id.comment_img);
        listView = $$(R.id.comment_listview);
        editText = $$(R.id.edit_addcomment);
        linearLayout = $$(R.id.addcomment_editor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        presenter = new CommentPresenter(this);
        dynBean = (Dyns.DynsBean.DynBean) getIntent().getSerializableExtra("dynbean");
        title.setText(dynBean.getTitle());
        content.setText(dynBean.getContent());
        Glide.with(this)
                .load(Config.url_str_dynimg_base+dynBean.getImgId())
                .into(img);

        presenter.update();
    }

    @Override
    public void showMsg(String msg) {
        MyToast.getInstance(this).ShowToast(msg);
    }

    @Override
    public void toActivity(Class<? extends Activity> activity, User user) {

    }

    @Override
    public void update(ArrayList<Comms.CommsBean> comms) {
       adapter = new CommentAdapter(comms,this,this,presenter);
       listView.setAdapter(adapter);
       adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (CommentPresenter) presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void click(View view) {
        presenter.addComments(dynBean.getDynId(),editText.getText().toString(),com_commit_id);
        hideCommentEditor();
    }
    public void click2(View view) {
        showCommentEditor(-1L,"评论");
    }
    public void click3(View view) {
        hideCommentEditor();
    }
}
