package com.yvrun.officeprocess.mvp.model;

import com.yvrun.officeprocess.mvp.net.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public abstract class BaseModel<T>{

    protected RetrofitService mRetrofitService;

    public BaseModel(RetrofitService retrofitService) {
        mRetrofitService = retrofitService;
    }

    public Observable<T> getData(){
        return null;
    }
}
