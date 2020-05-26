package com.chengzhen.kotlinappother.mvp.net

import android.util.Log

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class SimpleObserver<T> : Observer<T> {

    override fun onSubscribe(d: Disposable) {

        Log.d("--TAG--", "onSubscribe")
    }

    override fun onNext(t: T) {

        Log.d("--TAG--", "onNext")

        success(t)
    }

    override fun onError(e: Throwable) {

        Log.d("--TAG--", "onError")
        error(e)
    }

    override fun onComplete() {

        Log.d("--TAG--", "onComplete")
    }

    protected abstract fun success(t: T)
    protected abstract fun error(e: Throwable)

}
