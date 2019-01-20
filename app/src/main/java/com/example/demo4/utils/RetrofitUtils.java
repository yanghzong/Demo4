package com.example.demo4.utils;

import android.util.Log;

import com.example.demo4.R;
import com.example.demo4.inter.ICallBack;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private final OkHttpClient.Builder mBuilder;
    private final HttpLoggingInterceptor httpLoggingInterceptor;

    public RetrofitUtils(){
        HttpLoggingInterceptor.Level level=HttpLoggingInterceptor.Level.BODY;
        //创建日志拦截器
        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("信息打印", "log: "+message);
            }
        });
        httpLoggingInterceptor.setLevel(level);
        mBuilder = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(3, TimeUnit.SECONDS);
    }

    public static RetrofitUtils getInstance(){
        if(instance == null){
            synchronized (RetrofitUtils.class){
                if(instance == null){
                    return instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public void doLogin(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();
        MyInterFace myInterFace = retrofit.create(MyInterFace.class);
        Call<ResponseBody> login = myInterFace.login(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }
    public void doZhuCe(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();
        MyInterFace myInterFace = retrofit.create(MyInterFace.class);
        Call<ResponseBody> login = myInterFace.ZhuCe(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }

   public void doGeRen(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();
        MyInterFace myInterFace = retrofit.create(MyInterFace.class);
        Call<ResponseBody> login = myInterFace.GeRen(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }

    public void doShowshop(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();
        MyInterFace myInterFace = retrofit.create(MyInterFace.class);
        Call<ResponseBody> login = myInterFace.GeRen(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }

   public void Goods(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();

       MyInterFace myInterFace = retrofit.create(MyInterFace.class);
       Call<ResponseBody> login = myInterFace.Goods(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }

    public void XiangQing(String url, Map<String,String> map, final ICallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mBuilder.build())
                .build();
        MyInterFace myInterFace = retrofit.create(MyInterFace.class);
        Call<ResponseBody> login = myInterFace.XiangQing(map);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailed();
            }
        });
    }
}
