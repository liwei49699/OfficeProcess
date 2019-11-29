package com.yvrun.officeprocess.mvp.view;

import androidx.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseMvpFragment<P extends BasePresenter,M extends BaseModel> extends BaseFragment implements IBaseView, HasAndroidInjector {

    @Inject
    protected P mPresenter;
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void bindMvp() {
        AndroidSupportInjection.inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T> 数据类型
     * @return 数据流
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this,Lifecycle.Event.ON_DESTROY));
    }
}
