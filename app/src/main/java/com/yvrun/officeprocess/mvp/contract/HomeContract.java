package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.HomeArticelResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.model.bean.TestResponseBean;
import com.yvrun.officeprocess.mvp.net.DialogObserver;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;


public class HomeContract {

    //处理网络请求
    public static class HomeModelImp extends BaseModel<HomeArticelResponseBean>{

        @Inject
        public HomeModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<HomeArticelResponseBean> getHomeArticle(int page){
            return mRetrofitService.getHomeArticle(page);
        }
    }

    //进行数据回调
    public interface IHomeView extends IBaseView{

        void getHomeArticle(HomeArticelResponseBean homeArticelResponseBean);
    }

    //处理业务逻辑
    public static class HomePresenterImp extends BasePresenter<IHomeView, HomeModelImp>{

        @Inject
        public HomePresenterImp(HomeModelImp model, IHomeView view) {
            super(model, view);
        }

        public void getData(int page){

            mModel.getHomeArticle(page)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<HomeArticelResponseBean>() {
                        @Override
                        protected void success(HomeArticelResponseBean homeArticelResponseBean) {
                            mView.getHomeArticle(homeArticelResponseBean);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.showError("访问服务器错误");
                        }
                    });
        }


    }
}
