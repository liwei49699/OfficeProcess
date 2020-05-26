package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.user.LoginResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;


public class LogoutContract {

    //处理网络请求
    public static class LoginModelImp extends BaseModel{

        @Inject
        public LoginModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<LoginResponseBean> getLogin(String account,String password){
            return mRetrofitService.getLogin(account,password);
        }

        public Observable<LoginResponseBean> getRegister(String account,String password,String passwordAgain){
            return mRetrofitService.getRegister(account,password,passwordAgain);
        }
    }

    //进行数据回调
    public interface ILoginView extends IBaseView{

        void getLogin(LoginResponseBean loginResponseBean);
        void getRegister(LoginResponseBean loginResponseBean);
    }

    //处理业务逻辑
    public static class LoginPresenterImp extends BasePresenter<ILoginView, LoginModelImp>{

        @Inject
        public LoginPresenterImp(LoginModelImp model, ILoginView view) {
            super(model, view);
        }

        public void getLogin(String account,String password){

            mModel.getLogin(account,password)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<LoginResponseBean>() {
                        @Override
                        protected void success(LoginResponseBean loginResponseBean) {
                            mView.getLogin(loginResponseBean);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.showError("访问服务器错误");                        }
                    });
        }

        public void getRegister(String account,String password,String passwordAgain){

            mModel.getRegister(account,password,passwordAgain)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<LoginResponseBean>() {
                        @Override
                        protected void success(LoginResponseBean loginResponseBean) {
                            mView.getRegister(loginResponseBean);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.showError("访问服务器错误");                        }
                    });
        }
    }
}
