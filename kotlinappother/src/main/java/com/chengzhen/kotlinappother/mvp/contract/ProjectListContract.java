package com.yvrun.officeprocess.mvp.contract;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.project.ProjectListResponseBean;
import com.yvrun.officeprocess.core.primary.project.ProjectResponseBean;
import com.yvrun.officeprocess.core.primary.wechat.WeChatListResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ProjectListContract {

    //处理网络请求
    public static class ProjectListModelImp extends BaseModel<ArticleResponseBean> {

        @Inject
        public ProjectListModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<ProjectListResponseBean> getProjectList(int page, int id){
            return mRetrofitService.getProjectList(page,id);
        }
    }

    //进行数据回调
    public interface IProjectListView extends IBaseView {

        void getProjectList(ProjectListResponseBean projectListResponseBean, boolean success);
    }

    //处理业务逻辑
    public static class ProjectListPresenterImp extends BasePresenter<IProjectListView, ProjectListModelImp> {

        @Inject
        public ProjectListPresenterImp(ProjectListModelImp model, IProjectListView view) {
            super(model, view);
        }

        public void getProjectList(int page,int id){

            mModel.getProjectList(page,id)
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<ProjectListResponseBean>() {
                        @Override
                        protected void success(ProjectListResponseBean projectListResponseBean) {

                            mView.getProjectList(projectListResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {

                            mView.getProjectList(null,false);
                        }
                    });


        }
    }
}
