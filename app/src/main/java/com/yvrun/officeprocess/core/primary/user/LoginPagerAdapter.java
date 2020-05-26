package com.yvrun.officeprocess.core.primary.wechat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * FragmentPagerAdapter 适用于子Fragment数量较少的情况，destroyItem会销毁fragment实例
 * FragmentStatePagerAdapter 适用于子Fragment数量较多的情况，destroyItem时只会detach并不会将fragment销毁
 */
public class WeChatPagerAdapter extends FragmentStatePagerAdapter {

    private List<WeChatTopResponseBean.DataBean> mWeChatList;

    public WeChatPagerAdapter(@NonNull FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public WeChatPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setFragmentList(List<WeChatTopResponseBean.DataBean> weChatList) {
        mWeChatList = weChatList;
    }

    private Fragment createFragments(WeChatTopResponseBean.DataBean weChatBean) {
        return WeChatListFragment.getInstance(weChatBean);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return createFragments(mWeChatList.get(position));
    }

    @Override
    public int getCount() {
        return mWeChatList == null ? 0 : mWeChatList.size();
    }
}
