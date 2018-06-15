package com.sto.asportclient.adddyn;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nanchen.compresshelper.CompressHelper;
import com.sto.asportclient.BaseActivity;
import com.sto.asportclient.BasePresenter;
import com.sto.asportclient.R;
import com.sto.asportclient.util.ImgUtil;
import com.sto.asportclient.util.MyToast;
import com.sto.asportclient.util.WaitDialog;

import java.io.File;
import java.io.InputStream;

public class AddDynActivity extends BaseActivity implements AddDynContract.View {
    private ImageView imageView;
    private static final int CROP_PHOTO = 2;
    private static final int REQUEST_CODE_PICK_IMAGE=3;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;
    private File updateFile = null;
    private Uri imageUri;
    private EditText editText_title;
    private EditText editText_content;
    AddDynContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dyn);
        initView();
        presenter = new AddDynPresenter(this,this);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        switch (req) {

            case REQUEST_CODE_PICK_IMAGE:
                if (res == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        updateFile = new File(getRealPathFromURI(uri));
                        InputStream in = getContentResolver().openInputStream(uri);
                        Bitmap bit = BitmapFactory.decodeStream(in);
                        imageView.setImageBitmap(bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this,"程序崩溃",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Log.i("tag", "失败");
                }

                break;
            default:
                break;
        }
        super.onActivityResult(req, res, data);
    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        WaitDialog dialog = WaitDialog.getInstance(this);
        dialog.setTitle("上传");
        dialog.setContent("正在上传...");
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
    public void finshAct() {
        finish();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (AddDynContract.Presenter) presenter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    void initView(){
        imageView=(ImageView)findViewById(R.id.image);
        editText_title = $$(R.id.add_edit_title);
        editText_content = $$(R.id.add_edit_content);
    }

    public void choosePhone(View view){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);

        }else {
            choosePhoto();
        }
    }

    /**
     * 从相册选取图片
     */
    void choosePhoto(){
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                choosePhoto();
            } else
            {
                // Permission Denied
                Toast.makeText(AddDynActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void upload(View view) {
        Log.i("file:","com.sto.asportclient.adddyn.AddDynActivity:"+updateFile);
         if(updateFile != null && !"gif".equals(ImgUtil.getImageType(updateFile.getPath())))
             if(updateFile != null) updateFile =  CompressHelper.getDefault(this).compressToFile(updateFile);
        presenter.addDyn(editText_title.getText().toString(),editText_content.getText().toString(),updateFile);
    }
}
