package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.Url;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        // log用拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);// 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .addInterceptor(new ParamsInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Url.BASE_ADDRESS)
                .client(okHttpClient);
        return builder.build();
    }

    @Provides
    @Singleton
    public RetrofitService provideApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }
}
