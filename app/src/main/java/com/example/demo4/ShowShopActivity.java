package com.example.demo4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.demo4.adapter.ShowShopAdapter;
import com.example.demo4.bean.ShowShopBean;
import com.example.demo4.presenter.LoginPresenter;
import com.example.demo4.presenter.LoginPresenterinterFace;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowShopActivity extends AppCompatActivity {

    @BindView(R.id.xrv_showshop)
    XRecyclerView xrvShowshop;
    private LoginPresenter loginPresenter;
    private List<ShowShopBean.DataBean> showlist;
    private ShowShopAdapter showShopAdapter;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shop);
        ButterKnife.bind(this);
        //初始化p层
        initpresenter();
        //初始化adapter
        initAdapter();

        //设置xrv的刷新加载
        xrvShowshop.setPullRefreshEnabled(true);
        xrvShowshop.setLoadingMoreEnabled(true);
        xrvShowshop.setLoadingListener(new XRecyclerView.LoadingListener() {



            @Override
            public void onRefresh() {
                page = 1;
                initpresenter();
            }

            @Override
            public void onLoadMore() {
                page++;
                initpresenter();

            }
        });
    }

    private void initAdapter() {
        showlist = new ArrayList<>();
        showShopAdapter = new ShowShopAdapter(this,showlist);
        xrvShowshop.setAdapter(showShopAdapter);
        xrvShowshop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        showShopAdapter.setOnitemClickLis(new ShowShopAdapter.OnitemClickLis() {
            @Override
            public void itemclick(int pid) {
                Intent intent = new Intent(ShowShopActivity.this, XiangQingActivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }

    private void initpresenter() {
        loginPresenter = new LoginPresenter();
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+page);
        map.put("pscid","1");
        loginPresenter.Show(map, new LoginPresenterinterFace() {
            @Override
            public void onResponse(String json) {
                Gson gson=new Gson();
                ShowShopBean showShopBean = gson.fromJson(json, ShowShopBean.class);
                showShopAdapter.setList(showShopBean.getData(),page);
                xrvShowshop.refreshComplete();
            }

            @Override
            public void onFailure() {
                Toast.makeText(ShowShopActivity.this, "服务器链接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginPresenter != null){
            loginPresenter = null;
        }
    }
}
