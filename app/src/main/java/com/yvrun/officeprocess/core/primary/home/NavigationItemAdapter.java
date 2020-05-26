package com.yvrun.officeprocess.core.primary.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.knowledge.SystemResponseBean;

public class SystemItemAdapter extends BaseQuickAdapter<SystemResponseBean.DataBean.ChildrenBean, BaseViewHolder> {

    public SystemItemAdapter() {
        super(R.layout.item_system_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SystemResponseBean.DataBean.ChildrenBean item) {

        helper.setText(R.id.tv_item_title,item.getName());
    }
}
