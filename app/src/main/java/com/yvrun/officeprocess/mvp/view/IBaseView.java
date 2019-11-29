package com.yvrun.officeprocess.mvp.view;

import com.uber.autodispose.AutoDisposeConverter;

public interface IBaseView {

    /**
     * 网络请求前后的操作
     */
    default void showBefore(){}
    default void hideAfter(){}

    default void showError(String msg){}

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
