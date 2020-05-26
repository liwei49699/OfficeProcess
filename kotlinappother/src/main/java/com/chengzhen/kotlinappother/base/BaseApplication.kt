package com.chengzhen.kotlinappother

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

import androidx.multidex.MultiDex

import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.squareup.leakcanary.LeakCanary


import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import me.jessyan.autosize.AutoSizeConfig

class BaseApplication : Application(), HasAndroidInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {

        lateinit var sContext: BaseApplication
            private set

        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.white, android.R.color.black)//全局设置主题颜色
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                ClassicsHeader(context)
            }
            //设置全局的Footer构建器
            //        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            //            @Override
            //            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
            //                //指定为经典Footer，默认是 BallPulseFooter
            //                return new ClassicsFooter(context).setDrawableSize(20);
            //            }
            //        });
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        sContext = this
        //初始化屏幕适配
        initAutoSize()
        //初始化全局dagger变量
        initDagger()

        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    private fun initDagger() {

        //        DaggerAppComponent.builder()
        //                .appModule(new AppModule(this))
        //                .httpModule(new HttpModule())
        //                .build().inject(this);
    }


    private fun initAutoSize() {

        //三方页面在其他页面时需初始化
        AutoSizeConfig.getInstance().run {
            isBaseOnWidth = true
            isExcludeFontScale = true
        }
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return dispatchingAndroidInjector
    }
}
