package com.chengzhen.kotlinappother.mvp.view

import androidx.lifecycle.Lifecycle

import com.chengzhen.kotlinappother.base.BaseActivity
import com.chengzhen.kotlinappother.mvp.presenter.BasePresenter
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector

abstract class BaseMvpActivity<P : BasePresenter<*, *>> : BaseActivity(), IBaseView, HasAndroidInjector {

    @Inject
    var mPresenter: P? = null
    @Inject
    internal var androidInjector: DispatchingAndroidInjector<Any>? = null

    override fun bindMvp() {
        AndroidInjection.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     * @param <T> 数据类型
     * @return 数据流
    </T> */
    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY))
    }
}
