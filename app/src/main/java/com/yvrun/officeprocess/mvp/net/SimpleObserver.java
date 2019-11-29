package com.yvrun.officeprocess.mvp.net;

import android.util.Log;

import com.yvrun.officeprocess.mvp.view.IBaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

        Log.d("--TAG--","onSubscribe");
    }

    @Override
    public void onNext(T t) {

        Log.d("--TAG--","onNext");

        success(t);
    }

    @Override
    public void onError(Throwable e) {

        Log.d("--TAG--","onError");
        error(e);
    }

    @Override
    public void onComplete() {

        Log.d("--TAG--","onComplete");
    }

    protected abstract void success(T t);
    protected abstract void error(Throwable e);

}
