package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.knowledge.NavigationResponseBean;
import com.yvrun.officeprocess.core.primary.knowledge.SystemResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NavigationContract {

    //处理网络请求
    public static class NavigationModelImp extends BaseModel<ArticleResponseBean> {

        @Inject
        public NavigationModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<NavigationResponseBean> getKnowledgeNavigation(){
            return mRetrofitService.getKnowledgeNavigation();
        }

    }

    //进行数据回调
    public interface INavigationView extends IBaseView {

        void getKnowledgeNavigation(NavigationResponseBean navigationResponseBean, boolean success);
    }

    //处理业务逻辑
    public static class NavigationPresenterImp extends BasePresenter<INavigationView, NavigationModelImp> {

        @Inject
        public NavigationPresenterImp(NavigationModelImp model, INavigationView view) {
            super(model, view);
        }

        public void getKnowledgeNavigation(){

            mModel.getKnowledgeNavigation()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<NavigationResponseBean>() {
                        @Override
                        protected void success(NavigationResponseBean navigationResponseBean) {

                            mView.getKnowledgeNavigation(navigationResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getKnowledgeNavigation(null,false);
                        }
                    });


        }
    }
}
