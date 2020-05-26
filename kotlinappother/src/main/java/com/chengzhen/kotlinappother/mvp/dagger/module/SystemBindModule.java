package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.home.HomeFragment;
import com.yvrun.officeprocess.core.primary.knowledge.SystemFragment;
import com.yvrun.officeprocess.mvp.contract.HomeContract;
import com.yvrun.officeprocess.mvp.contract.SystemContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SystemBindModule {
    @Binds
    abstract SystemContract.ISystemView bindView(SystemFragment fragment);
}
