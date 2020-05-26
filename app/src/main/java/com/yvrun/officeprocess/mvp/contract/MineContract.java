package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.home.HomeBannerResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class HomeContract {

    //处理网络请求
    public static class HomeModelImp extends BaseModel<ArticleResponseBean>{

        @Inject
        public HomeModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<ArticleResponseBean> getHomeArticle(int page){
            return mRetrofitService.getHomeArticle(page);
        }

        public Observable<HomeBannerResponseBean> getHomeBanner(){
            return mRetrofitService.getHomeBanner();
        }
    }

    //进行数据回调
    public interface IHomeView extends IBaseView{

        void getHomeArticle(ArticleResponseBean articleResponseBean, boolean success);
        void getHomeBanner(HomeBannerResponseBean homeBannerResponseBean);
    }

    //处理业务逻辑
    public static class HomePresenterImp extends BasePresenter<IHomeView, HomeModelImp>{

        @Inject
        public HomePresenterImp(HomeModelImp model, IHomeView view) {
            super(model, view);
        }

        public void getHomeArticle(int page){

            mModel.getHomeArticle(page)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<ArticleResponseBean>() {
                        @Override
                        protected void success(ArticleResponseBean articleResponseBean) {
                            mView.getHomeArticle(articleResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.getHomeArticle(null,false);
                        }
                    });
        }

        public void getHomeBanner() {
            mModel.getHomeBanner()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<HomeBannerResponseBean>() {
                        @Override
                        protected void success(HomeBannerResponseBean homeBannerResponseBean) {
                            mView.getHomeBanner(homeBannerResponseBean);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.showError("访问服务器错误");
                        }
                    });
        }
    }
}
