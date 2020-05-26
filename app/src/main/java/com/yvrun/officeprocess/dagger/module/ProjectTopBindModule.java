package com.yvrun.officeprocess.dagger.module;

import com.yvrun.officeprocess.core.primary.wechat.WeChatFragment;
import com.yvrun.officeprocess.core.primary.wechat.WeChatListFragment;
import com.yvrun.officeprocess.mvp.contract.WeChatContract;
import com.yvrun.officeprocess.mvp.contract.WeChatListContract;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WeChatListBindModule {
    @Binds
    abstract WeChatListContract.IWeChatListView bindView(WeChatListFragment fragment);
}
