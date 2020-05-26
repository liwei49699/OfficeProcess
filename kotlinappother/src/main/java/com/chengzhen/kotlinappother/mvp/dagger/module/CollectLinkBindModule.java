package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.user.CollectArticleFragment;
import com.yvrun.officeprocess.core.primary.user.CollectLinkFragment;
import com.yvrun.officeprocess.mvp.contract.CollectArticleContract;
import com.yvrun.officeprocess.mvp.contract.CollectLinkContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CollectLinkBindModule {
    @Binds
    abstract CollectLinkContract.ICollectLinkView bindView(CollectLinkFragment fragment);
}
