package com.yvrun.officeprocess.core.primary.main;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.home.ArticleDetailsActivity;
import com.yvrun.officeprocess.core.primary.home.ArticleResponseBean;
import com.yvrun.officeprocess.core.primary.home.HomeAdapter;
import com.yvrun.officeprocess.core.primary.knowledge.WeChatListResponsebean;
import com.yvrun.officeprocess.core.primary.knowledge.WeChatTopResponseBean;
import com.yvrun.officeprocess.global.Constant;
import com.yvrun.officeprocess.mvp.contract.SystemChildrenContract;
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
    private HomeAdapter mHomeAdapter;
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
        mHomeAdapter = new HomeAdapter();
        RecycleViewDivider itemDecoration = new RecycleViewDivider.Builder(mContext)
                .setOrientation(RecycleViewDivider.VERTICAL)
                .setStyle(RecycleViewDivider.Style.BETWEEN)
                .setColorRes(R.color.color_f5)
                .setSize(COMPLEX_UNIT_PX,10)
                .build();
        mRvWeChatList.addItemDecoration(itemDecoration);

        mRvWeChatList.setAdapter(mHomeAdapter);
        mEmptyView = getLayoutInflater().inflate(R.layout.empty_list_home, mRvWeChatList, false);

        mHomeAdapter.setEnableLoadMore(true);
        mHomeAdapter.setOnLoadMoreListener(() -> mPresenter.getWeChatList(mId,mCurrentPage),mRvWeChatList);

        mHomeAdapter.setOnItemClickListener((adapter, view, position) -> {

            ArticleResponseBean.DataBean.DatasBean datasBean = mHomeAdapter.getData().get(position);
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
    public void getWeChatList(WeChatListResponsebean weChatListResponsebean, boolean success) {

        if(mCurrentPage == 0) {
            loadHomePage(weChatListResponsebean,success);
        } else {
            loadMorePage(weChatListResponsebean,success);
        }
    }

    private void loadHomePage(WeChatListResponsebean weChatListResponsebean, boolean success) {

        mRefreshWeChatList.finishRefresh(success);
        if(success) {
            WeChatListResponsebean.DataBean data = weChatListResponsebean.getData();
            List<WeChatListResponsebean.DataBean.DatasBean> datas = data.getDatas();
            if(datas.size() == 0) {
                mHomeAdapter.setEmptyView(mEmptyView);
            } else {
                mHomeAdapter.setNewData(datas);
                mCurrentPage++;
            }
        } else {
            mHomeAdapter.setNewData(null);
            mHomeAdapter.setEmptyView(mEmptyView);
        }
    }


    private void loadMorePage(WeChatListResponsebean weChatListResponsebean, boolean success) {

        if(success) {
            WeChatListResponsebean.DataBean data = weChatListResponsebean.getData();
            List<WeChatListResponsebean.DataBean.DatasBean> datas = data.getDatas();
            if(datas.size() == 0) {
                mHomeAdapter.loadMoreEnd(true);
            } else {
                mCurrentPage++;
                mHomeAdapter.addData(datas);
                mHomeAdapter.loadMoreComplete();
            }
        } else {
            mHomeAdapter.loadMoreFail();
        }

    }


}
