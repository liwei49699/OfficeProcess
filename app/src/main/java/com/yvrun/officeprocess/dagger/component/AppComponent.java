package com.yvrun.officeprocess.dagger.component;


import com.yvrun.officeprocess.dagger.module.AppModule;
import com.yvrun.officeprocess.dagger.module.HttpModule;
import com.yvrun.officeprocess.dagger.module.NeedInjectModules;
import com.yvrun.officeprocess.global.BaseApplication;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Singleton
@Component(modules = {AndroidInjectionModule.class, NeedInjectModules.class,AppModule.class, HttpModule.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    void inject(BaseApplication application);
}
