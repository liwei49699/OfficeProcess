package com.yvrun.officeprocess.mvp.presenter;

import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.view.IBaseView;

public abstract class BasePresenter<V extends IBaseView,M extends BaseModel>{

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }
}
