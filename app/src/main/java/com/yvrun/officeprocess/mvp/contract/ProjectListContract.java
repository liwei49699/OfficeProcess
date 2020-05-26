package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.wechat.WeChatListResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeChatListContract {

    //处理网络请求
    public static class WeChatListModelImp extends BaseModel<ArticleResponseBean> {

        @Inject
        public WeChatListModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<WeChatListResponseBean> getWeChatList(int id, int page){
            return mRetrofitService.getWeChatList(id,page);
        }
    }

    //进行数据回调
    public interface IWeChatListView extends IBaseView {

        void getWeChatList(WeChatListResponseBean weChatListResponsebean, boolean success);
    }

    //处理业务逻辑
    public static class WeChatListPresenterImp extends BasePresenter<IWeChatListView, WeChatListModelImp> {

        @Inject
        public WeChatListPresenterImp(WeChatListModelImp model, IWeChatListView view) {
            super(model, view);
        }

        public void getWeChatList(int page,int id){

            mModel.getWeChatList(page,id)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<WeChatListResponseBean>() {
                        @Override
                        protected void success(WeChatListResponseBean weChatListResponsebean) {

                            mView.getWeChatList(weChatListResponsebean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getWeChatList(null,false);
                        }
                    });


        }
    }
}
