package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.home.HomeFragment;
import com.yvrun.officeprocess.dagger.ActivityScope;
import com.yvrun.officeprocess.mvp.contract.TestContract;
import com.yvrun.officeprocess.test.TestActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NeedInjectModules {


    //测试activity
//    @ActivityScope
    @ContributesAndroidInjector(modules = {TestBindModule.class,TestModule.class})
    abstract TestActivity injectTestActivity();


    //首页fragment
//    @ContributesAndroidInjector(modules = HomeModule.class)
//    abstract HomeFragment injectHomeFragment();


}
