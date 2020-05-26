package com.chengzhen.kotlinappother.mvp.presenter

import com.chengzhen.kotlinappother.mvp.model.BaseModel
import com.chengzhen.kotlinappother.mvp.view.IBaseView

abstract class BasePresenter<V : IBaseView, M : BaseModel<*>>(protected var mModel: M, protected var mView: V)
