package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.mvp.contract.TestContract;
import com.yvrun.officeprocess.test.TestActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestBindModule {

    @Binds
    abstract TestContract.ITestView bindView(TestActivity activity);
}
