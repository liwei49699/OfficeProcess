package com.yvrun.officeprocess.mvp.net;

import android.util.Log;

import com.yvrun.officeprocess.mvp.view.IBaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class DialogObserver<T> implements Observer<T> {

    private IBaseView mBaseView;

    public DialogObserver(IBaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void onSubscribe(Disposable d) {

        Log.d("--TAG--","onSubscribe");
        if (mBaseView != null){
            mBaseView.showBefore();
        }
    }

    @Override
    public void onNext(T t) {

        Log.d("--TAG--","onNext");

        success(t);
    }

    @Override
    public void onError(Throwable e) {

        Log.d("--TAG--","onError");
        if (mBaseView != null){
            mBaseView.hideAfter();
        }
        error(e);
    }

    @Override
    public void onComplete() {

        Log.d("--TAG--","onComplete");
        if (mBaseView != null){
            mBaseView.hideAfter();
        }
    }

    protected abstract void success(T t);
    protected abstract void error(Throwable e);

}
