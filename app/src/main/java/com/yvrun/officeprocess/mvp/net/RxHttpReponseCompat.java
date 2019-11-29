package com.yvrun.officeprocess.mvp.net;


import com.yvrun.officeprocess.mvp.model.bean.BaseRspBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxHttpReponseCompat {

    public static <T> ObservableTransformer<BaseRspBean<T>,T> compatResult(){

        return new ObservableTransformer<BaseRspBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseRspBean<T>> upstream) {

                return upstream.flatMap(new Function<BaseRspBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseRspBean<T> tBaseBean) throws Exception {

                        return null;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 切换到主线程
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> toMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
