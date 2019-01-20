package com.example.demo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo4.bean.XiangQingBean;
import com.example.demo4.presenter.LoginPresenter;
import com.example.demo4.presenter.LoginPresenterinterFace;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class XiangQingActivity extends AppCompatActivity {

    @BindView(R.id.showVideo)
    JCVideoPlayerStandard showVideo;
    @BindView(R.id.show_image)
    SimpleDraweeView showImage;
    @BindView(R.id.show_name)
    TextView showName;
    @BindView(R.id.show_price)
    TextView showPrice;
    @BindView(R.id.jia)
    Button jia;
    @BindView(R.id.pay)
    Button pay;
  String urlString="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    private String pid;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        //接收传过来的值
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        showVideo.setUp(urlString, showVideo.SCREEN_LAYOUT_NORMAL, "商品视频");
        initData();
    }

    private void initData() {
        loginPresenter = new LoginPresenter();
        Map<String, String> map = new HashMap<>();
        map.put("pid", pid);
        loginPresenter.Xiang(map, new LoginPresenterinterFace() {
            @Override
            public void onResponse(String json) {
                if (json.equals("{}")) {
                    Toast.makeText(XiangQingActivity.this, "没有此商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                XiangQingBean xiangQingBean = gson.fromJson(json, XiangQingBean.class);
                XiangQingBean.DataBean data = xiangQingBean.getData();
                String[] split = data.getImages().trim().split("!");
                showImage.setImageURI(split[0]);
                showName.setText(data.getTitle());
                showPrice.setText(data.getPrice() + "");

            }

            @Override
            public void onFailure() {
                Toast.makeText(XiangQingActivity.this, "连接服务失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter= null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @OnClick({R.id.jia, R.id.pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jia:
                Toast.makeText(XiangQingActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pay:
                Toast.makeText(XiangQingActivity.this, "支付完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
