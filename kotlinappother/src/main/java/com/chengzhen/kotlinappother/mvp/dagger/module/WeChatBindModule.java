package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.wechat.WeChatFragment;
import com.yvrun.officeprocess.mvp.contract.WeChatContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WeChatBindModule {
    @Binds
    abstract WeChatContract.IWeChatView bindView(WeChatFragment fragment);
}
