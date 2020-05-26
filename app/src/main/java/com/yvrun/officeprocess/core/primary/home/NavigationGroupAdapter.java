package com.yvrun.officeprocess.core.primary.home;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.knowledge.SystemDetailsActivity;
import com.yvrun.officeprocess.core.primary.knowledge.SystemResponseBean;
import com.yvrun.officeprocess.global.Constant;
import com.yvrun.officeprocess.wight.decoration.RecycleViewDivider;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class SystemGroupAdapter extends BaseQuickAdapter<SystemResponseBean.DataBean, BaseViewHolder> {

    public SystemGroupAdapter() {
        super(R.layout.item_system_group);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SystemResponseBean.DataBean item) {

        helper.setText(R.id.tv_group_title,item.getName());

        RecyclerView rvGroup = helper.getView(R.id.rv_group);

        SystemItemAdapter systemItemAdapter = new SystemItemAdapter();

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);

        rvGroup.setLayoutManager(layoutManager);
        rvGroup.setAdapter(systemItemAdapter);
        systemItemAdapter.setNewData(item.getChildren());
        systemItemAdapter.setOnItemClickListener((adapter, view, position) -> {

            Intent intent = new Intent(mContext, SystemDetailsActivity.class);
            intent.putExtra(Constant.SYSTEM_GROUP,item);
            intent.putExtra(Constant.POSITION,position);
            mContext.startActivity(intent);

        });
    }
}
