package com.yvrun.officeprocess.mvp.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求基本配置类
 */
public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;

    private RetrofitHelper() {

        throw new UnsupportedOperationException("RetrofitHelper can't init");
    }

    static {

        initOkHttpClient();
    }

    /**
     * 初始化OKHttpClient,设置超时时间,设置打印日志,设置Request拦截器
     */
    private static void initOkHttpClient() {
        //打印所有okhttp请求日志
        if (mOkHttpClient == null) {

            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {

                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    // 包含header、body数据
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //http数据log，日志中打印出HTTP请求&响应数据
                            .addInterceptor(loggingInterceptor)
                            .retryOnConnectionFailure(true)
                            //当失败时重复请求
                            .connectTimeout(10, TimeUnit.SECONDS)
                            //连接超时时间
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            //这个一般是接口地址（一定要以/结尾）
                            .client(mOkHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            //集成rxjava
                            .addConverterFactory(GsonConverterFactory.create())
                            //gson解析
                            .build();

        return retrofit.create(clazz);
    }

    /**
     * 创建一个请求
     */
    public static RetrofitService getServiceApi() {

        return createApi(RetrofitService.class, Url.BASE_ADDRESS);
    }
}
