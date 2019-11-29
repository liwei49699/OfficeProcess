package com.yvrun.officeprocess.mvp.view;

import androidx.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.yvrun.officeprocess.global.BaseApplication;
import com.yvrun.officeprocess.dagger.component.AppComponent;
import com.yvrun.officeprocess.mvp.model.BaseModel;
import com.yvrun.officeprocess.mvp.presenter.BasePresenter;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView, HasAndroidInjector {

    @Inject
    protected P mPresenter;
//    @Inject
//    protected M mModel;
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void bindMvp() {

        AndroidInjection.inject(this);
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
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
