package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.user.CollectionLinkResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class CollectLinkContract {

    //处理网络请求
    public static class CollectLinkModelImp extends BaseModel{

        @Inject
        public CollectLinkModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<CollectionLinkResponseBean> getCollectLinkList(){
            return mRetrofitService.getCollectLinkList();
        }
    }

    //进行数据回调
    public interface ICollectLinkView extends IBaseView{

        void getCollectLinkList(CollectionLinkResponseBean collectionLinkResponseBean, boolean success);
    }

    //处理业务逻辑
    public static class CollectLinkPresenterImp extends BasePresenter<ICollectLinkView, CollectLinkModelImp>{

        @Inject
        public CollectLinkPresenterImp(CollectLinkModelImp model, ICollectLinkView view) {
            super(model, view);
        }

        public void getCollectLinkList(){

            mModel.getCollectLinkList()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<CollectionLinkResponseBean>() {
                        @Override
                        protected void success(CollectionLinkResponseBean collectionLinkResponseBean) {
                            mView.getCollectLinkList(collectionLinkResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.getCollectLinkList(null,false);
                        }
                    });
        }
    }
}
