package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.search.SearchInfoFragment;
import com.yvrun.officeprocess.core.primary.search.SearchResultFragment;
import com.yvrun.officeprocess.mvp.contract.SearchInfoContract;
import com.yvrun.officeprocess.mvp.contract.SearchResultContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SearchResultBindModule {
    @Binds
    abstract SearchResultContract.ISearchResultView bindView(SearchResultFragment fragment);
}
