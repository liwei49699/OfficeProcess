package com.chengzhen.kotlinappother.mvp.net

import android.util.Log


import com.chengzhen.kotlinappother.mvp.view.IBaseView

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class DialogObserver<T>(private val mBaseView: IBaseView?) : Observer<T> {

    override fun onSubscribe(d: Disposable) {

        Log.d("--TAG--", "onSubscribe")
        mBaseView?.showBefore()
    }

    override fun onNext(t: T) {

        Log.d("--TAG--", "onNext")

        success(t)
    }

    override fun onError(e: Throwable) {

        Log.d("--TAG--", "onError")
        mBaseView?.hideAfter()
        error(e)
    }

    override fun onComplete() {

        Log.d("--TAG--", "onComplete")
        mBaseView?.hideAfter()
    }

    protected abstract fun success(t: T)
    protected abstract fun error(e: Throwable)

}
