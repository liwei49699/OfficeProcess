package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.search.SearchHotBean;
import com.yvrun.officeprocess.core.primary.search.SearchListBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class SearchResultContract {

    //处理网络请求
    public static class SearchResultModelImp extends BaseModel{

        @Inject
        public SearchResultModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<SearchListBean> search(int page, String key){
            return mRetrofitService.search(page,key);
        }
    }

    //进行数据回调
    public interface ISearchResultView extends IBaseView{

        void search(SearchListBean searchListBean, boolean success);
    }

    //处理业务逻辑
    public static class SearchResultPresenterImp extends BasePresenter<ISearchResultView, SearchResultModelImp>{

        @Inject
        public SearchResultPresenterImp(SearchResultModelImp model, ISearchResultView view) {
            super(model, view);
        }

        public void search(int page, String key){
            mModel.search(page,key)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<SearchListBean>() {
                        @Override
                        protected void success(SearchListBean searchListBean) {
                            mView.search(searchListBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.search(null,false);
                        }
                    });
        }
    }
}
