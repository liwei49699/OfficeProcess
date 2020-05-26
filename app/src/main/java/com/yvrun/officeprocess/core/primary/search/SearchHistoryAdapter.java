package com.yvrun.officeprocess.core.primary.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yvrun.officeprocess.R;

import java.util.List;

public class SearchHotAdapter extends BaseQuickAdapter<SearchHotBean.DataBean, BaseViewHolder> {

    public SearchHotAdapter() {
        super(R.layout.item_search_hot);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SearchHotBean.DataBean item) {

        helper.setText(R.id.tv_hot_word,item.getName());
    }
}
