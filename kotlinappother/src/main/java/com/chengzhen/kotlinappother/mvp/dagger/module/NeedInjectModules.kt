package com.chengzhen.kotlinappother.mvp.dagger.module

import com.chengzhen.kotlinappother.fragment.HomeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NeedInjectModules {

    //测试activity
    //    @ActivityScope
    //    @ContributesAndroidInjector(modules = {TestBindModule.class,TestModule.class})
    //    abstract TestActivity injectTestActivity();

    //首页fragment
    @ContributesAndroidInjector(modules = [HomeBindModule::class, HomeModule::class])
    internal abstract fun injectHomeFragment(): HomeFragment

    //体系
    //    @ContributesAndroidInjector(modules = {SystemBindModule.class})
    //    abstract SystemFragment injectSystemFragment();
    //
    //    //体系列表
    //    @ContributesAndroidInjector(modules = {SystemChildrenBindModule.class})
    //    abstract SystemChildrenFragment injectSystemChildrenFragment();
    //
    //    //导航列表
    //    @ContributesAndroidInjector(modules = {NavigationBindModule.class})
    //    abstract NavigationFragment injectNavigationFragment();
    //
    //    //公众号主列表
    //    @ContributesAndroidInjector(modules = {WeChatBindModule.class})
    //    abstract WeChatFragment injectWeChatFragment();
    //
    //    //公众号列表
    //    @ContributesAndroidInjector(modules = {WeChatListBindModule.class})
    //    abstract WeChatListFragment injectWeChatListFragment();
    //
    //    //项目类别列表
    //    @ContributesAndroidInjector(modules = {ProjectTopBindModule.class})
    //    abstract ProjectFragment injectProjectFragment();
    //
    //    //项目列表
    //    @ContributesAndroidInjector(modules = {ProjectListBindModule.class})
    //    abstract ProjectListFragment injectProjectListFragment();
    //
    //    //我的
    //    @ContributesAndroidInjector(modules = {MineBindModule.class})
    //    abstract MineFragment injectMineFragment();
    //
    //    //登录
    //    @ContributesAndroidInjector(modules = {LoginBindModule.class})
    //    abstract LoginFragment injectLoginFragment();
    //
    //    //注册
    //    @ContributesAndroidInjector(modules = {RegisterBindModule.class})
    //    abstract RegisterFragment injectRegisterFragment();
    //
    //    //设置
    //    @ContributesAndroidInjector(modules = {SettingBindModule.class})
    //    abstract SettingActivity injectSettingActivity();
    //
    //    //收藏文章
    //    @ContributesAndroidInjector(modules = {CollectArticleBindModule.class})
    //    abstract CollectArticleFragment injectCollcetArticleFragment();
    //
    //    //收藏网站
    //    @ContributesAndroidInjector(modules = {CollectLinkBindModule.class})
    //    abstract CollectLinkFragment injectCollcetLinkFragment();
    //
    //    //收藏网站
    //    @ContributesAndroidInjector()
    //    abstract ArticleDetailsActivity injectArticleDetailsActivity();
    //
    //    //搜索词
    //    @ContributesAndroidInjector(modules = {SearchInfoBindModule.class})
    //    abstract SearchInfoFragment injectSearchInfoFragment();
    //
    //    //搜索结果
    //    @ContributesAndroidInjector(modules = {SearchResultBindModule.class})
    //    abstract SearchResultFragment injectSearchResultFragment();
}
