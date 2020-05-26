package com.chengzhen.kotlinappother.mvp.contract

import com.chengzhen.kotlinappother.data.ArticleResponseBean
import com.chengzhen.kotlinappother.data.HomeBannerResponseBean
import com.chengzhen.kotlinappother.mvp.model.BaseModel
import com.chengzhen.kotlinappother.mvp.net.RetrofitService
import com.chengzhen.kotlinappother.mvp.net.RxHttpReponseCompat
import com.chengzhen.kotlinappother.mvp.net.SimpleObserver
import com.chengzhen.kotlinappother.mvp.presenter.BasePresenter
import com.chengzhen.kotlinappother.mvp.view.IBaseView

import javax.inject.Inject

import io.reactivex.Observable


class HomeContract {

    //处理网络请求
    class HomeModelImp @Inject
    constructor(retrofitService: RetrofitService) : BaseModel<ArticleResponseBean>(retrofitService) {

        val homeBanner: Observable<HomeBannerResponseBean>
            get() = mRetrofitService.getHomeBanner()

        fun getHomeArticle(page: Int): Observable<ArticleResponseBean> {
            return mRetrofitService.getHomeArticle(page)
        }

        //        public Observable<BaseRspBean> collectArticle(int id){
        //            return getMRetrofitService().collectArticle(id);
        //        }
        //
        //        public Observable<BaseRspBean> uncollectArticle(int id){
        //            return getMRetrofitService().unCollectArticle(id);
        //        }
    }

    //进行数据回调
    interface IHomeView : IBaseView {

        fun getHomeArticle(articleResponseBean: ArticleResponseBean?, success: Boolean)
        fun getHomeBanner(homeBannerResponseBean: HomeBannerResponseBean)
    }

    //处理业务逻辑
    class HomePresenterImp @Inject
    constructor(model: HomeModelImp, view: IHomeView) : BasePresenter<IHomeView, HomeModelImp>(model, view) {

        fun getHomeArticle(page: Int) {

            mModel.getHomeArticle(page)
                    .compose(RxHttpReponseCompat.toMain())
                    .`as`<ObservableSubscribeProxy<ArticleResponseBean>>(mView.bindAutoDispose())
                    .subscribe(object : SimpleObserver<ArticleResponseBean>() {
                        override fun success(articleResponseBean: ArticleResponseBean) {
                            mView.getHomeArticle(articleResponseBean, true)
                        }

                        override fun error(e: Throwable) {
                            mView.getHomeArticle(null, false)
                        }
                    })
        }

        fun getHomeBanner() {
            mModel.homeBanner
                    .compose(RxHttpReponseCompat.toMain())
                    .`as`<ObservableSubscribeProxy<HomeBannerResponseBean>>(mView.bindAutoDispose())
                    .subscribe(object : SimpleObserver<HomeBannerResponseBean>() {
                        override fun success(homeBannerResponseBean: HomeBannerResponseBean) {
                            mView.getHomeBanner(homeBannerResponseBean)
                        }

                        override fun error(e: Throwable) {
                            mView.showError("访问服务器错误")
                        }
                    })
        }

        //        public void collectArticle(int id) {
        //            mModel.collectArticle(id)
        //                    .compose(RxHttpReponseCompat.toMain())
        //                    .as(mView.bindAutoDispose())
        //                    .subscribe(new SimpleObserver<BaseRspBean>() {
        //                        @Override
        //                        protected void success(BaseRspBean baseRspBean) {
        //
        //                            new CollectArticleEvent(id,true).post();
        //                        }
        //
        //                        @Override
        //                        protected void error(Throwable e) {
        //                            mView.showError("访问服务器错误");
        //                        }
        //                    });
        //        }
        //
        //        public void uncollectArticle(int id) {
        //            mModel.uncollectArticle(id)
        //                    .compose(RxHttpReponseCompat.toMain())
        ////                    .as(mView.bindAutoDispose())
        //                    .subscribe(new SimpleObserver<BaseRspBean>() {
        //                        @Override
        //                        protected void success(BaseRspBean baseRspBean) {
        //                            new CollectArticleEvent(id,false).post();
        //                        }
        //
        //                        @Override
        //                        protected void error(Throwable e) {
        //                            mView.showError("访问服务器错误");
        //                        }
        //                    });
        //        }
    }
}
