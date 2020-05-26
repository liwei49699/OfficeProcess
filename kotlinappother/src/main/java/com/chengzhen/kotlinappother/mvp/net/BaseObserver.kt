package com.chengzhen.kotlinappother.mvp.net


import com.chengzhen.kotlinappother.mvp.contract.TestContract

import java.lang.ref.WeakReference

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T>(testPresenterImp: TestContract.TestPresenterImp, view: WeakReference<TestContract.ITestView>) : Observer<T> {

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }

    override fun onComplete() {

    }

    protected abstract fun success(t: T)
    protected abstract fun error(e: Throwable)
    protected abstract fun addDisposable(d: Disposable)
}
