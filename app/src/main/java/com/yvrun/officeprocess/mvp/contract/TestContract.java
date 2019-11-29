package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.model.bean.TestResponseBean;
import com.yvrun.officeprocess.mvp.net.DialogObserver;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;


public class TestContract {

    //处理网络请求
    public static class TestModelImp extends BaseModel<TestResponseBean>{

        @Inject
        public TestModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        @Override
        public Observable<TestResponseBean> getData() {
            return mRetrofitService.getArticle(0);
        }
    }

    //进行数据回调
    public interface ITestView extends IBaseView{
        void operateData(TestResponseBean responseBean);
    }

    //处理业务逻辑
    public static class TestPresenterImp extends BasePresenter<ITestView, TestModelImp>{

        @Inject
        public TestPresenterImp(TestModelImp model, ITestView view) {
            super(model, view);
        }

        public void getData(){
            mModel.getData()
                    .delaySubscription(3, TimeUnit.SECONDS)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new DialogObserver<TestResponseBean>(mView) {
                        @Override
                        protected void success(TestResponseBean responseBean) {
                            mView.operateData(responseBean);
                        }

                        @Override
                        protected void error(Throwable e) {

                        }
                    });
        }
    }
}
