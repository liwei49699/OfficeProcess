package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.knowledge.SystemChildrenFragment;
import com.yvrun.officeprocess.core.primary.knowledge.SystemFragment;
import com.yvrun.officeprocess.mvp.contract.SystemChildrenContract;
import com.yvrun.officeprocess.mvp.contract.SystemContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SystemChildrenBindModule {
    @Binds
    abstract SystemChildrenContract.ISystemChildrenView bindView(SystemChildrenFragment fragment);
}
