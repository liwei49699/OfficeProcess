package com.chengzhen.kotlinappother.mvp.contract


import com.chengzhen.kotlinappother.mvp.model.BaseModel
import com.chengzhen.kotlinappother.mvp.model.bean.BaseRspBean
import com.chengzhen.kotlinappother.mvp.net.RetrofitService
import com.chengzhen.kotlinappother.mvp.net.RxHttpResponseCompat
import com.chengzhen.kotlinappother.mvp.net.SimpleObserver
import com.chengzhen.kotlinappother.mvp.presenter.BasePresenter
import com.chengzhen.kotlinappother.mvp.view.IBaseView

import javax.inject.Inject

import io.reactivex.Observable


class SettingContract {

    //处理网络请求
    class SettingModelImp @Inject
    constructor(retrofitService: RetrofitService) : BaseModel<*>(retrofitService) {

        val logout: Observable<BaseRspBean<*>>
            get() = mRetrofitService.getLogout()
    }

    //进行数据回调
    interface ISettingView : IBaseView {

        fun getLogout(BaseRspBean: BaseRspBean<*>)
    }

    //处理业务逻辑
    class SettingPresenterImp @Inject
    constructor(model: SettingModelImp, view: ISettingView) : BasePresenter<ISettingView, SettingModelImp>(model, view) {

        fun getLogout() {

            mModel.logout
                    .compose<BaseRspBean>(RxHttpResponseCompat.toMain())
                    .`as`<ObservableSubscribeProxy<BaseRspBean>>(mView.bindAutoDispose<BaseRspBean>())
                    .subscribe(object : SimpleObserver<BaseRspBean<*>>() {
                        override fun success(baseRspBean: BaseRspBean<*>) {
                            mView.getLogout(baseRspBean)
                        }

                        override fun error(e: Throwable) {
                            mView.showError("访问服务器错误")
                        }
                    })
        }
    }
}
