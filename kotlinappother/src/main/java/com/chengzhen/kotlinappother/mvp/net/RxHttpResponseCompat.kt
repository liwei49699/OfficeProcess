package com.chengzhen.kotlinappother.mvp.net


import com.chengzhen.kotlinappother.mvp.model.bean.BaseRspBean

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

object RxHttpReponseCompat {

    fun <T> compatResult(): ObservableTransformer<BaseRspBean<T>, T> {

        return ObservableTransformer { upstream -> upstream.flatMap(Function<BaseRspBean<T>, ObservableSource<T>> { null }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }

    /**
     * 切换到主线程
     * @param <T>
     * @return
    </T> */
    fun <T> toMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
