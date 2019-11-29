package com.yvrun.officeprocess.core.primary.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.mvp.contract.HomeContract;
import com.yvrun.officeprocess.mvp.view.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment<HomeContract.HomePresenterImp, HomeContract.HomeModelImp> implements HomeContract.IHomeView {

    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout mSrlHome;
    private int mCurrentPage = 0;
    private HomeAdapter mHomeAdapter;
    private View mEmptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        mSrlHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(5000/*,false*/);//传入false表示刷新失败
//                mSrlHome.setEnableLoadMore(true);

            }
        });
        mSrlHome.setDragRate(0.3f);//显示下拉高度/手指真实下拉高度=阻尼效果
//        mSrlHome.setFooterMaxDragRate(1F);
        mSrlHome.setFooterTriggerRate(1.5F);
        mRvHome.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeAdapter = new HomeAdapter();
        mRvHome.setAdapter(mHomeAdapter);
//            mSrlHome.setEnableLoadMore(false);
        mHomeAdapter.setEnableLoadMore(true);
        mEmptyView = getLayoutInflater().inflate(R.layout.empty_list_home, mRvHome, false);

        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        },mRvHome);


    }

    @Override
    public void getData() {
        mPresenter.getData(mCurrentPage);
    }

    @Override
    public void getHomeArticle(HomeArticelResponseBean homeArticelResponseBean) {

        if(mCurrentPage == 0) {
//            loadHomePage(homeArticelResponseBean);
            mHomeAdapter.setNewData(null);
            mHomeAdapter.setEmptyView(mEmptyView);
//            mSrlHome.setEnableLoadMore(false);
        } else {
            loadMorePage(homeArticelResponseBean);
        }
    }

    private void loadMorePage(HomeArticelResponseBean homeArticelResponseBean) {

        HomeArticelResponseBean.DataBean data = homeArticelResponseBean.getData();
        List<HomeArticelResponseBean.DataBean.DatasBean> datas = data.getDatas();
    }

    private void loadHomePage(HomeArticelResponseBean homeArticelResponseBean) {

        HomeArticelResponseBean.DataBean data = homeArticelResponseBean.getData();
        List<HomeArticelResponseBean.DataBean.DatasBean> datas = data.getDatas();
        mHomeAdapter.setNewData(datas);
    }

//    @Override
//    protected void setupActivityComponent(AppComponent appComponent) {
//
//        DaggerFragmentCompont.builder().appComponent(appComponent)
//                .homeModule(new HomeModule(this))
//                .build()
//                .inject(this);
//    }
}
