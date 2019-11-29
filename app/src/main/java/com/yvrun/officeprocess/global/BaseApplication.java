package com.yvrun.officeprocess.global;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.clj.fastble.BleManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.yvrun.officeprocess.dagger.component.AppComponent;
import com.yvrun.officeprocess.dagger.component.DaggerAppComponent;
import com.yvrun.officeprocess.dagger.module.AppModule;
import com.yvrun.officeprocess.dagger.module.HttpModule;

import org.litepal.LitePal;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import me.jessyan.autosize.AutoSizeConfig;

public class BaseApplication extends Application implements HasAndroidInjector {

    @SuppressLint("StaticFieldLeak")
    public static Context sContext;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
        //初始化屏幕适配
        initAutoSize();
        //初始化全局dagger变量
        initDagger();
        //初始化蓝牙配置
        initFastBle();
        //初始化数据库
        LitePal.initialize(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initFastBle() {

        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setSplitWriteNum(20)
                .setConnectOverTime(10000)
                .setOperateTimeout(5000)
                .init(this);
    }

    private void initDagger() {

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);
    }


    private void initAutoSize() {

        //三方页面在其他页面时需初始化
        AutoSizeConfig.getInstance()
                //屏蔽系统字体大小
                .setExcludeFontScale(true);
    }

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
