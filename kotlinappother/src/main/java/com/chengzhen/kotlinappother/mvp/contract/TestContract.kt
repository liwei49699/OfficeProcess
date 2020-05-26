package com.chengzhen.kotlinappother.mvp.contract

import com.chengzhen.kotlinappother.mvp.model.BaseModel
import com.chengzhen.kotlinappother.mvp.model.bean.TestResponseBean
import com.chengzhen.kotlinappother.mvp.net.DialogObserver
import com.chengzhen.kotlinappother.mvp.net.RetrofitService
import com.chengzhen.kotlinappother.mvp.net.RxHttpReponseCompat
import com.chengzhen.kotlinappother.mvp.presenter.BasePresenter
import com.chengzhen.kotlinappother.mvp.view.IBaseView

import java.util.concurrent.TimeUnit

import javax.inject.Inject

import io.reactivex.Observable


class TestContract {

    //处理网络请求
    class TestModelImp @Inject
    constructor(retrofitService: RetrofitService) : BaseModel<TestResponseBean>(retrofitService) {

        override val data: Observable<TestResponseBean>?
            get() = mRetrofitService.getArticle(0)
    }

    //进行数据回调
    interface ITestView : IBaseView {
        fun operateData(responseBean: TestResponseBean)
    }

    //处理业务逻辑
    class TestPresenterImp @Inject
    constructor(model: TestModelImp, view: ITestView) : BasePresenter<ITestView, TestModelImp>(model, view) {

        fun getData() {
            mModel.data!!
                    .delaySubscription(3, TimeUnit.SECONDS)
                    .compose(RxHttpReponseCompat.toMain())
                    .`as`<ObservableSubscribeProxy<TestResponseBean>>(mView.bindAutoDispose())
                    .subscribe(object : DialogObserver<TestResponseBean>(mView) {
                        override fun success(responseBean: TestResponseBean) {
                            mView.operateData(responseBean)
                        }

                        override fun error(e: Throwable) {

                        }
                    })
        }
    }
}
