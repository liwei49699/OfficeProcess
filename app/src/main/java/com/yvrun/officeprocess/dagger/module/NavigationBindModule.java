package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.home.HomeFragment;
import com.yvrun.officeprocess.mvp.contract.HomeContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeBindModule {
    @Binds
    abstract HomeContract.IHomeView bindView(HomeFragment fragment);
}
