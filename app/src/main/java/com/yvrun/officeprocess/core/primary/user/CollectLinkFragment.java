package com.yvrun.officeprocess.core.primary.user;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.mvp.view.BaseMvpFragment;

import butterknife.BindView;

public class CollectArticleFragment extends BaseMvpFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collcet_article;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }
}
