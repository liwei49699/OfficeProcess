package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.home.HomeFragment;
import com.yvrun.officeprocess.mvp.contract.HomeContract;
import com.yvrun.officeprocess.mvp.contract.TestContract;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class HomeModule {
    @Binds
    abstract HomeContract.IHomeView bindView(HomeFragment fragment);
}
