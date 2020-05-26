package com.chengzhen.kotlinappother.mvp.dagger.component


import com.chengzhen.kotlinappother.base.BaseApplication
import com.chengzhen.kotlinappother.mvp.dagger.module.AppModule
import com.chengzhen.kotlinappother.mvp.dagger.module.HttpModule
import com.chengzhen.kotlinappother.mvp.dagger.module.NeedInjectModules

import javax.inject.Singleton

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@Singleton
@Component(modules = [AndroidInjectionModule::class, NeedInjectModules::class, AppModule::class, HttpModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    override fun inject(application: BaseApplication)
}
