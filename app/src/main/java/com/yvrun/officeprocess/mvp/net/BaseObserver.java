package com.yvrun.officeprocess.mvp.net;


import com.yvrun.officeprocess.mvp.contract.TestContract;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {


    public BaseObserver(TestContract.TestPresenterImp testPresenterImp, WeakReference<TestContract.ITestView> view) {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    protected abstract void success(T t);
    protected abstract void error(Throwable e);
    protected abstract void addDisposable(Disposable d);
}
