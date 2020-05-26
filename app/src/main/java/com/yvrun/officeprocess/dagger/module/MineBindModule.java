package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.project.ProjectFragment;
import com.yvrun.officeprocess.core.primary.project.ProjectListFragment;
import com.yvrun.officeprocess.mvp.contract.ProjectContract;
import com.yvrun.officeprocess.mvp.contract.ProjectListContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProjectListBindModule {
    @Binds
    abstract ProjectListContract.IProjectListView bindView(ProjectListFragment fragment);
}
