package com.yvrun.officeprocess.core.primary.wechat;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.home.ArticleDetailsActivity;
import com.yvrun.officeprocess.global.Constant;
import com.yvrun.officeprocess.mvp.contract.WeChatListContract;
import com.yvrun.officeprocess.mvp.view.BaseMvpFragment;
import com.yvrun.officeprocess.wight.decoration.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class WeChatListFragment extends BaseMvpFragment<WeChatListContract.WeChatListPresenterImp,
        WeChatListContract.WeChatListModelImp> implements WeChatListContract.IWeChatListView {

    @BindView(R.id.rv_we_chat_list)
    RecyclerView mRvWeChatList;
    @BindView(R.id.refresh_we_chat_list)
    SmartRefreshLayout mRefreshWeChatList;
    private int mCurrentPage;
    private WeChatListAdapter mWeChatListAdapter;
    private View mEmptyView;
    private int mId;

    public static WeChatListFragment getInstance(WeChatTopResponseBean.DataBean weChatBean) {

        WeChatListFragment systemChildrenFragment = new WeChatListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.WE_CHAT_LIST, weChatBean);
        systemChildrenFragment.setArguments(bundle);
        return systemChildrenFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_we_chat_list;
    }

    @Override
    protected void initView() {

        Bundle arguments = getArguments();
        WeChatTopResponseBean.DataBean weChatBean = arguments.getParcelable(Constant.WE_CHAT_LIST);
        mId = weChatBean.getId();

        mRefreshWeChatList.setOnRefreshListener(refreshlayout -> {
            mCurrentPage = 0;
            mPresenter.getWeChatList(mId,mCurrentPage);
        });

        mRefreshWeChatList.setDragRate(0.3f);//显示下拉高度/手指真实下拉高度=阻尼效果
        mRvWeChatList.setLayoutManager(new LinearLayoutManager(mContext));
        mWeChatListAdapter = new WeChatListAdapter();
        RecycleViewDivider itemDecoration = new RecycleViewDivider.Builder(mContext)
                .setOrientation(RecycleViewDivider.VERTICAL)
                .setStyle(RecycleViewDivider.Style.BETWEEN)
                .setColorRes(R.color.color_f5)
                .setSize(COMPLEX_UNIT_PX,10)
                .build();
        mRvWeChatList.addItemDecoration(itemDecoration);

        mRvWeChatList.setAdapter(mWeChatListAdapter);
        mEmptyView = getLayoutInflater().inflate(R.layout.empty_list_home, mRvWeChatList, false);

        mWeChatListAdapter.setEnableLoadMore(true);
        mWeChatListAdapter.setOnLoadMoreListener(() -> mPresenter.getWeChatList(mId,mCurrentPage),mRvWeChatList);

        mWeChatListAdapter.setOnItemClickListener((adapter, view, position) -> {

            WeChatListResponseBean.DataBean.DatasBean datasBean = mWeChatListAdapter.getData().get(position);
            String title = datasBean.getTitle();
            String link = datasBean.getLink();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.ARTICLE_TITLE,title);
            bundle.putString(Constant.ARTICLE_URL, link);
            startActivity(ArticleDetailsActivity.class,bundle);
        });
    }

    @Override
    protected void getData() {

        mPresenter.getWeChatList(mId,mCurrentPage);
    }

    @Override
    public void getWeChatList(WeChatListResponseBean weChatListResponsebean, boolean success) {

        if(mCurrentPage == 0) {
            loadHomePage(weChatListResponsebean,success);
        } else {
            loadMorePage(weChatListResponsebean,success);
        }
    }

    private void loadHomePage(WeChatListResponseBean weChatListResponsebean, boolean success) {

        mRefreshWeChatList.finishRefresh(success);
        if(success) {
            WeChatListResponseBean.DataBean data = weChatListResponsebean.getData();
            List<WeChatListResponseBean.DataBean.DatasBean> datas = data.getDatas();
            if(datas.size() == 0) {
                mWeChatListAdapter.setEmptyView(mEmptyView);
            } else {
                mWeChatListAdapter.setNewData(datas);
                mCurrentPage++;
            }
        } else {
            mWeChatListAdapter.setNewData(null);
            mWeChatListAdapter.setEmptyView(mEmptyView);
        }
    }


    private void loadMorePage(WeChatListResponseBean weChatListResponsebean, boolean success) {

        if(success) {
            WeChatListResponseBean.DataBean data = weChatListResponsebean.getData();
            List<WeChatListResponseBean.DataBean.DatasBean> datas = data.getDatas();
            if(datas.size() == 0) {
                mWeChatListAdapter.loadMoreEnd(true);
            } else {
                mCurrentPage++;
                mWeChatListAdapter.addData(datas);
                mWeChatListAdapter.loadMoreComplete();
            }
        } else {
            mWeChatListAdapter.loadMoreFail();
        }

    }


}
