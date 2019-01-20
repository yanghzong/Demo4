package com.example.demo4;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo4.presenter.LoginPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeRenActivity extends AppCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.clicks)
    Button clicks;
    @BindView(R.id.tuichu)
    TextView tuichu;
    private LoginPresenter loginPresenter;
    private SharedPreferences mLogin;
    private String path = Environment.getExternalStorageState() + "/iamge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter();
    }

    @OnClick({R.id.tou, R.id.clicks, R.id.tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tou:
                new AlertDialog.Builder(GeRenActivity.this)
                        .setTitle("更换头像")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("请选择")
                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 1000);
                            }
                        })
                        .setPositiveButton("相机", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                                startActivityForResult(intent, 1500);
                            }
                        })
                        .create()
                        .show();
                
                break;

            case R.id.clicks:
                Intent intent=new Intent(GeRenActivity.this,ShowShopActivity.class);
                startActivity(intent);

                break;
            case R.id.tuichu:
                startActivity(new Intent(GeRenActivity.this,MainActivity.class));
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {//相片的截取操作
            Uri uri = data.getData();
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(uri, "image/*");

            intent.putExtra("crop", true);

            intent.putExtra("aspectX", 1);
            intent.putExtra("outputX", 250);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputY", 250);

            intent.putExtra("return-data", true);


            startActivityForResult(intent, 2000);
        }
        if (requestCode == 1500 && resultCode == RESULT_OK) {
            Intent intent = new Intent("com.android.camera.action.CROP");

            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");

            intent.putExtra("crop", true);

            intent.putExtra("aspectX", 1);
            intent.putExtra("outputX", 250);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputY", 250);

            intent.putExtra("return-data", true);
        }
        if (requestCode == 2000 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            tou.setImageBitmap(bitmap);
            Toast.makeText(GeRenActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginPresenter != null){
            loginPresenter = null;
        }
    }
}
