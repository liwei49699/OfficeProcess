package com.chengzhen.kotlinappother.mvp.model


import com.chengzhen.kotlinappother.mvp.net.RetrofitService

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

abstract class BaseModel<T>(protected var mRetrofitService: RetrofitService) {

    val data: Observable<T>?
        get() = null
}
