package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.HomeArticelResponseBean;
import com.yvrun.officeprocess.core.primary.knowledge.SystemResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SystemContract {

    //处理网络请求
    public static class SystemModelImp extends BaseModel<HomeArticelResponseBean> {

        @Inject
        public SystemModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<SystemResponseBean> getKnowledgeSystem(){
            return mRetrofitService.getKnowledgeSystem();
        }

    }

    //进行数据回调
    public interface ISystemView extends IBaseView {

        void getKnowledgeSystem(SystemResponseBean systemResponseBean,boolean success);
    }

    //处理业务逻辑
    public static class SystemPresenterImp extends BasePresenter<ISystemView, SystemModelImp> {

        @Inject
        public SystemPresenterImp(SystemModelImp model, ISystemView view) {
            super(model, view);
        }

        public void getKnowledgeSystem(){

            mModel.getKnowledgeSystem()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<SystemResponseBean>() {
                        @Override
                        protected void success(SystemResponseBean systemResponseBean) {

                            mView.getKnowledgeSystem(systemResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getKnowledgeSystem(null,false);
                        }
                    });


        }
    }
}
