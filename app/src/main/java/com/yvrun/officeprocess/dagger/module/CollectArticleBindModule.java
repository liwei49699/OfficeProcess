package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.user.RegisterFragment;
import com.yvrun.officeprocess.core.primary.user.SettingActivity;
import com.yvrun.officeprocess.mvp.contract.LoginContract;
import com.yvrun.officeprocess.mvp.contract.SettingContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SettingBindModule {
    @Binds
    abstract SettingContract.ISettingView bindView(SettingActivity activity);
}
