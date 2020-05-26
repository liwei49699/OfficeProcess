package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.mine.MineFragment;
import com.yvrun.officeprocess.core.primary.user.LoginFragment;
import com.yvrun.officeprocess.mvp.contract.LoginContract;
import com.yvrun.officeprocess.mvp.contract.MineContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginBindModule {
    @Binds
    abstract LoginContract.ILoginView bindView(LoginFragment fragment);
}
