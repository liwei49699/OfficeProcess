package com.yvrun.officeprocess.dagger.module;

import android.app.Application;

import com.yvrun.officeprocess.dagger.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public  class TestModule {

//    @ActivityScope
    @Provides
    public Object provideObject(){
        return new Object();
    }
}
