package com.yvrun.officeprocess.core.primary.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.knowledge.SystemResponseBean;

import java.util.List;

public class SystemGroupAdapter extends BaseQuickAdapter<SystemResponseBean.DataBean, BaseViewHolder> {

    public SystemGroupAdapter() {
        super(R.layout.item_system_group);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SystemResponseBean.DataBean item) {

        helper.setText(R.id.tv_group_title,item.getName());

        RecyclerView rvGroup = helper.getView(R.id.rv_group);



    }
}
