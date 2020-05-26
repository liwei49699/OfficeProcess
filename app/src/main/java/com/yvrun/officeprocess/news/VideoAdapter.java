package com.yvrun.officeprocess.news;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yvrun.officeprocess.R;


public class VideoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public VideoAdapter() {
        super(R.layout.item_news);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_title,item);
    }
}
