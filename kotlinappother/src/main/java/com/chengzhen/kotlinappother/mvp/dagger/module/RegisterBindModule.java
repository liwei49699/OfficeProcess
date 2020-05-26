package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.user.LoginFragment;
import com.yvrun.officeprocess.core.primary.user.RegisterFragment;
import com.yvrun.officeprocess.mvp.contract.LoginContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RegisterBindModule {
    @Binds
    abstract LoginContract.ILoginView bindView(RegisterFragment fragment);
}
