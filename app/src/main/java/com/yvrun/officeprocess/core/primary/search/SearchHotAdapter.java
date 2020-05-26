package com.yvrun.officeprocess.core.primary.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchTopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchTopAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

    }
}
