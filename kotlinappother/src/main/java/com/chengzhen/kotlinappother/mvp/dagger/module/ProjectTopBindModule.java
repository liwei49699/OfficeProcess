package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.project.ProjectFragment;
import com.yvrun.officeprocess.core.primary.wechat.WeChatListFragment;
import com.yvrun.officeprocess.mvp.contract.ProjectContract;
import com.yvrun.officeprocess.mvp.contract.WeChatListContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProjectTopBindModule {
    @Binds
    abstract ProjectContract.IProjectView bindView(ProjectFragment fragment);
}
