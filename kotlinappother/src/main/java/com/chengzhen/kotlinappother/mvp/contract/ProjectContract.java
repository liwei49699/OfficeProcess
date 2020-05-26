package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.project.ProjectResponseBean;
import com.yvrun.officeprocess.core.primary.wechat.WeChatTopResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ProjectContract {

    //处理网络请求
    public static class ProjectModelImp extends BaseModel<ArticleResponseBean> {

        @Inject
        public ProjectModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<ProjectResponseBean> getProjectTop(){
            return mRetrofitService.getProjectTop();
        }
    }

    //进行数据回调
    public interface IProjectView extends IBaseView {

        void getProjectTop(ProjectResponseBean projectResponseBean, boolean success);
    }

    //处理业务逻辑
    public static class ProjectPresenterImp extends BasePresenter<IProjectView, ProjectModelImp> {

        @Inject
        public ProjectPresenterImp(ProjectModelImp model, IProjectView view) {
            super(model, view);
        }

        public void getProjectTop(){

            mModel.getProjectTop()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<ProjectResponseBean>() {
                        @Override
                        protected void success(ProjectResponseBean projectResponseBean) {

                            mView.getProjectTop(projectResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getProjectTop(null,false);
                        }
                    });


        }
    }
}
