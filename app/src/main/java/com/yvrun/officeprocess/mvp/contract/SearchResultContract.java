package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.search.SearchHotBean;
import com.yvrun.officeprocess.core.primary.user.CollectArticleresponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class SearchInfoContract {

    //处理网络请求
    public static class SearchInfoModelImp extends BaseModel{

        @Inject
        public SearchInfoModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<SearchHotBean> getHotKeyList(){
            return mRetrofitService.getHotKeyList();
        }
    }

    //进行数据回调
    public interface ISearchInfoView extends IBaseView{

        void getHotKeyList(SearchHotBean searchHotBean, boolean success);
    }

    //处理业务逻辑
    public static class SearchInfoPresenterImp extends BasePresenter<ISearchInfoView, SearchInfoModelImp>{

        @Inject
        public SearchInfoPresenterImp(SearchInfoModelImp model, ISearchInfoView view) {
            super(model, view);
        }

        public void getHotKeyList(){
            mModel.getHotKeyList()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<SearchHotBean>() {
                        @Override
                        protected void success(SearchHotBean searchHotBean) {
                            mView.getHotKeyList(searchHotBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.getHotKeyList(null,false);
                        }
                    });
        }
    }
}
