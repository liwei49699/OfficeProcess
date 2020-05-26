package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.mine.MineFragment;
import com.yvrun.officeprocess.core.primary.project.ProjectListFragment;
import com.yvrun.officeprocess.mvp.contract.MineContract;
import com.yvrun.officeprocess.mvp.contract.ProjectListContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MineBindModule {
    @Binds
    abstract MineContract.IMineView bindView(MineFragment fragment);
}
