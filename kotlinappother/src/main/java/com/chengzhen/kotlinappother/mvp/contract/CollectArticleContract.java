package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.user.CollectArticleresponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class CollectArticleContract {

    //处理网络请求
    public static class CollectArticleModelImp extends BaseModel{

        @Inject
        public CollectArticleModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<CollectArticleresponseBean> getCollectArticleList(int page){
            return mRetrofitService.getCollectArticleList(page);
        }
    }

    //进行数据回调
    public interface ICollectArticleView extends IBaseView{

        void getCollectArticleList(CollectArticleresponseBean collectArticleresponseBean, boolean success);
    }

    //处理业务逻辑
    public static class CollectArticlePresenterImp extends BasePresenter<ICollectArticleView, CollectArticleModelImp>{

        @Inject
        public CollectArticlePresenterImp(CollectArticleModelImp model, ICollectArticleView view) {
            super(model, view);
        }

        public void getCollectArticleList(int page){

            mModel.getCollectArticleList(page)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<CollectArticleresponseBean>() {
                        @Override
                        protected void success(CollectArticleresponseBean collectArticleresponseBean) {
                            mView.getCollectArticleList(collectArticleresponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.getCollectArticleList(null,false);
                        }
                    });
        }
    }
}
