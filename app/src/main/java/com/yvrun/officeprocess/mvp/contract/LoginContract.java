package com.yvrun.officeprocess.mvp.contract;

import android.text.TextUtils;

import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.home.HomeBannerResponseBean;
import com.yvrun.officeprocess.core.primary.mine.UserScoreResponseBean;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.net.RetrofitService;
import com.yvrun.officeprocess.mvp.net.RxHttpReponseCompat;
import com.yvrun.officeprocess.mvp.net.SimpleObserver;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;
import com.yvrun.officeprocess.mvp.view.IBaseView;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MineContract {

    //处理网络请求
    public static class MineModelImp extends BaseModel<ArticleResponseBean>{

        @Inject
        public MineModelImp(RetrofitService retrofitService) {
            super(retrofitService);
        }

        public Observable<UserScoreResponseBean> getUserScore(){
            return mRetrofitService.getUserScore();
        }

        public Observable<ResponseBody> getUserLevel(){
            return mRetrofitService.getUserLevel();
        }
    }

    //进行数据回调
    public interface IMineView extends IBaseView{

        void getUserScore(UserScoreResponseBean userScoreResponseBean, boolean success);
        void getUserLevel(String[] userLevel);
    }

    //处理业务逻辑
    public static class MinePresenterImp extends BasePresenter<IMineView, MineModelImp>{

        @Inject
        public MinePresenterImp(MineModelImp model, IMineView view) {
            super(model, view);
        }

        public void getUserScore(){

            mModel.getUserScore()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<UserScoreResponseBean>() {
                        @Override
                        protected void success(UserScoreResponseBean userScoreResponseBean) {
                            mView.getUserScore(userScoreResponseBean,true);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.getUserScore(null,false);
                        }
                    });
        }

        public void getUserLevel() {
            mModel.getUserLevel()
                    .compose(RxHttpReponseCompat.toMain())
                    .as(mView.bindAutoDispose())
                    .subscribe(new SimpleObserver<ResponseBody>() {
                        @Override
                        protected void success(ResponseBody responseBody){

                            String[] leverInfo = new String[0];
                            if(responseBody != null) {
                                try {
                                    String bodyString= responseBody.string();
                                    if (!TextUtils.isEmpty(bodyString)) {
                                        int index = bodyString.indexOf("本站积分");
                                        int coinIndexStart = bodyString.indexOf(">", index);
                                        int coinIndexEnd = bodyString.indexOf("<", coinIndexStart);
                                        String coinStr = bodyString.substring(coinIndexStart + 1, coinIndexEnd).trim();
                                        int lvIndexStart = bodyString.indexOf(">", coinIndexEnd);
                                        int lvIndexEnd = bodyString.indexOf("<", lvIndexStart);
                                        String lvStr = bodyString.substring(lvIndexStart + 1, lvIndexEnd).replace("lv", "").trim();
                                        int rankingIndexStart = bodyString.indexOf("排名", lvIndexEnd);
                                        int rankingIndexEnd = bodyString.indexOf("<", rankingIndexStart);
                                        String rankingStr = bodyString.substring(rankingIndexStart + 2, rankingIndexEnd).replace(" ", "").trim();
                                        Integer.parseInt(coinStr);
                                        Integer.parseInt(lvStr);
                                        Integer.parseInt(rankingStr);
                                        leverInfo =  new String[]{coinStr, lvStr, rankingStr};
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            mView.getUserLevel(leverInfo);
                        }

                        @Override
                        protected void error(Throwable e) {
                            mView.showError("访问服务器错误");
                        }
                    });
        }
    }
}
