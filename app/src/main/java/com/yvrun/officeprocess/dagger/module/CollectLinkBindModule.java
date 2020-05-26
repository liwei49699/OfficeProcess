package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.user.CollectArticleFragment;
import com.yvrun.officeprocess.core.primary.user.SettingActivity;
import com.yvrun.officeprocess.mvp.contract.CollectArticleContract;
import com.yvrun.officeprocess.mvp.contract.SettingContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CollectArticleBindModule {
    @Binds
    abstract CollectArticleContract.ICollectArticleView bindView(CollectArticleFragment fragment);
}
