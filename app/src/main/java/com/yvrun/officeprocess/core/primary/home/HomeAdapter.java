package com.yvrun.officeprocess.core.primary.home;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.global.GlideApp;
import com.yvrun.officeprocess.wight.CollectView;

public class HomeAdapter extends BaseQuickAdapter<HomeArticelResponseBean.DataBean.DatasBean, BaseViewHolder> {

    private OnCollectViewClickListener mOnCollectViewClickListener = null;

    public HomeAdapter() {
        super(R.layout.item_home);
    }

    public void setOnCollectViewClickListener(OnCollectViewClickListener onCollectViewClickListener) {
        mOnCollectViewClickListener = onCollectViewClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticelResponseBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()));
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_time, item.getNiceDate());
        helper.setText(R.id.tv_super_chapter_name, item.getSuperChapterName());
        helper.setText(R.id.tv_chapter_name, item.getChapterName());
        LinearLayout ll_new = helper.getView(R.id.ll_new);
        if (item.isFresh()) {
            ll_new.setVisibility(View.VISIBLE);
        } else {
            ll_new.setVisibility(View.GONE);
        }
        ImageView iv_img = helper.getView(R.id.iv_img);
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideApp.with(iv_img.getContext()).load(item.getEnvelopePic()).into(iv_img);
            iv_img.setVisibility(View.VISIBLE);
        } else {
            iv_img.setVisibility(View.GONE);
        }
        CollectView cv_collect = helper.getView(R.id.cv_collect);
        if (item.isCollect()) {
            cv_collect.setChecked(true);
        } else {
            cv_collect.setChecked(false);
        }
        TextView tv_tag = helper.getView(R.id.tv_tag);
        if (item.getTags() != null && item.getTags().size() > 0) {
            tv_tag.setText(item.getTags().get(0).getName());
            tv_tag.setVisibility(View.VISIBLE);
        } else {
            tv_tag.setVisibility(View.GONE);
        }
        cv_collect.setOnClickListener(new CollectView.OnClickListener() {
            @Override
            public void onClick(CollectView v) {
                if (mOnCollectViewClickListener != null) {
                    mOnCollectViewClickListener.onClick(helper, v, helper.getAdapterPosition() - getHeaderLayoutCount());
                }
            }
        });
    }

    public interface OnCollectViewClickListener {
        void onClick(BaseViewHolder helper, CollectView v, int position);
    }
}
