package com.chengzhen.kotlinappother.mvp.dagger.module

import com.chengzhen.kotlinappother.fragment.HomeFragment
import com.chengzhen.kotlinappother.mvp.contract.HomeContract

import dagger.Binds
import dagger.Module

@Module
abstract class HomeBindModule {
    @Binds
    internal abstract fun bindView(fragment: HomeFragment): HomeContract.IHomeView
}
