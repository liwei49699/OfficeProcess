package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SystemChildrenContract {

    //处理网络请求
    public static class SystemChildrenModelImp extends BaseModel<ArticleResponseBean> {

        @Inject
        public SystemChildrenModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<ArticleResponseBean> getKnowledgeArticleList(int page, int id){
            return mRetrofitService.getKnowledgeArticleList(page,id);
        }
    }

    //进行数据回调
    public interface ISystemChildrenView extends IBaseView {

        void getKnowledgeArticleList(ArticleResponseBean articleResponseBean, boolean success);
    }

    //处理业务逻辑
    public static class SystemChildrenPresenterImp extends BasePresenter<ISystemChildrenView, SystemChildrenModelImp> {

        @Inject
        public SystemChildrenPresenterImp(SystemChildrenModelImp model, ISystemChildrenView view) {
            super(model, view);
        }

        public void getKnowledgeArticleList(int page,int id){

            mModel.getKnowledgeArticleList(page,id)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<ArticleResponseBean>() {
                        @Override
                        protected void success(ArticleResponseBean articleResponseBean) {

                            mView.getKnowledgeArticleList(articleResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getKnowledgeArticleList(null,false);
                        }
                    });


        }
    }
}
