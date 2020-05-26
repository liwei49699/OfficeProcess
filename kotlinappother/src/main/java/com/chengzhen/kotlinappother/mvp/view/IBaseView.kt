package com.chengzhen.kotlinappother.mvp.view

import com.uber.autodispose.AutoDisposeConverter

interface IBaseView {

    /**
     * 网络请求前后的操作
     */
    fun showBefore() {}

    fun hideAfter() {}

    fun showError(msg: String) {}

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
    </T> */
    fun <T> bindAutoDispose(): AutoDisposeConverter<T>
}
