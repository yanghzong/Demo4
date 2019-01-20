package com.example.demo4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo4.bean.LoginBean;
import com.example.demo4.presenter.LoginPresenter;
import com.example.demo4.presenter.LoginPresenterinterFace;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  {


    @BindView(R.id.tv_zhuce)
    TextView tvZhuce;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_password)
    EditText edPassword;
    private SharedPreferences  mLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
        loginPresenter= new LoginPresenter();
    }


    @OnClick({R.id.tv_zhuce, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_zhuce:
                Intent intent = new Intent(MainActivity.this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String phone = edPhone.getText().toString().trim();
                String pwd = edPassword.getText().toString().trim();
                Map<String,String> map = new HashMap<>();//传值
                map.put("mobile",phone);
                map.put("password",pwd);
                if(!loginPresenter.IsNotNull(phone,pwd)){
                    Toast.makeText(MainActivity.this,"请输入正确格式的手机号和密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenter.Login(map, new LoginPresenterinterFace() {
                    @Override
                    public void onResponse(String json) {
                        //验证返回数据的正确性
                        if(json.equals("{\"msg\":\"密码格式有问题，不能少于6位数\",\"code\":\"1\"}")){
                            Toast.makeText(MainActivity.this,"密码格式有问题，不能少于6位数",Toast.LENGTH_SHORT).show();
                        }else if(json.equals("{\"msg\":\"请输入正确的手机号码\",\"code\":\"1\"}")){
                            Toast.makeText(MainActivity.this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                        }else if(json.equals("{\"msg\":\"天呢！用户不存在\",\"code\":\"1\"}")){
                            Toast.makeText(MainActivity.this,"请先注册",Toast.LENGTH_SHORT).show();
                        }else {
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                            startActivity(new Intent(MainActivity.this,GeRenActivity.class));
                            finish();
                        }


                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this,"链接服务器失败",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }




}
