package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.home.HomeFragment;
import com.yvrun.officeprocess.core.primary.knowledge.NavigationFragment;
import com.yvrun.officeprocess.mvp.contract.HomeContract;
import com.yvrun.officeprocess.mvp.contract.NavigationContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationBindModule {
    @Binds
    abstract NavigationContract.INavigationView bindView(NavigationFragment fragment);
}
