package com.chengzhen.kotlinappother.mvp.dagger.module


import com.chengzhen.kotlinappother.activity.SettingActivity
import com.chengzhen.kotlinappother.mvp.contract.SettingContract

import dagger.Binds
import dagger.Module

@Module
abstract class SettingBindModule {
    @Binds
    internal abstract fun bindView(activity: SettingActivity): SettingContract.ISettingView
}
