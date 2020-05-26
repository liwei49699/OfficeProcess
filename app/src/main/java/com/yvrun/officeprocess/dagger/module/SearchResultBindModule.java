package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.search.SearchInfoFragment;
import com.yvrun.officeprocess.core.primary.user.CollectLinkFragment;
import com.yvrun.officeprocess.mvp.contract.CollectLinkContract;
import com.yvrun.officeprocess.mvp.contract.SearchInfoContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SearchInfoBindModule {
    @Binds
    abstract SearchInfoContract.ISearchInfoView bindView(SearchInfoFragment fragment);
}
