package com.example.demo4.presenter;

import android.text.TextUtils;

import com.example.demo4.inter.Apis;
import com.example.demo4.inter.ICallBack;
import com.example.demo4.utils.RetrofitUtils;

import java.util.Map;

public class LoginPresenter {

    public boolean IsNotNull(String num,String pwd){
        if(TextUtils.isEmpty(num) || TextUtils.isEmpty(pwd)){
            return false;
        }
        return true;
    }
    public boolean IsNotNullChongfu(String num,String num1){
        if(!num.equals(num1)){
            return false;
        }
        return true;
    }
    public void Login(Map<String,String> map, final LoginPresenterinterFace loginPresenterinterFace){
        new RetrofitUtils().getInstance().doLogin(Apis.Login, map, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                loginPresenterinterFace.onResponse(json);
            }

            @Override
            public void onFailed() {
               loginPresenterinterFace.onFailure();
            }

        });

    }
    public void Zhuce(Map<String,String> map, final LoginPresenterinterFace loginPresenterinterFace){
        new RetrofitUtils().getInstance().doShowshop(Apis.ZHUCE, map, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                loginPresenterinterFace.onResponse(json);
            }

            @Override
            public void onFailed() {
             loginPresenterinterFace.onFailure();
            }


        });
    }
    public void GeRen(Map<String,String> map, final LoginPresenterinterFace loginPresenterinterFace){
        new RetrofitUtils().getInstance().doGeRen(Apis.GEREN, map, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                loginPresenterinterFace.onResponse(json);
            }

            @Override
            public void onFailed() {
               loginPresenterinterFace.onFailure();
            }


        });
    }

    public void Show(Map<String,String> map, final LoginPresenterinterFace loginPresenterinterFace){
        new RetrofitUtils().getInstance().Goods(Apis.GOODS, map, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                loginPresenterinterFace.onResponse(json);
            }

            @Override
            public void onFailed() {
                loginPresenterinterFace.onFailure();
            }


        });
    }

    public void Xiang(Map<String,String> map, final LoginPresenterinterFace loginPresenterinterFace){
        new RetrofitUtils().getInstance().XiangQing(Apis.XIANGQING, map, new ICallBack() {
            @Override
            public void onSuccess(String json) {
                loginPresenterinterFace.onResponse(json);
            }

            @Override
            public void onFailed() {
            loginPresenterinterFace.onFailure();
            }


        });
    }
}
