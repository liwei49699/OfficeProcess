package com.yvrun.officeprocess.core.primary.knowledge;

import android.database.sqlite.SQLiteDatabase;

import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.mvp.view.BaseFragment;

import org.litepal.tablemanager.Connector;

public class KnowledgeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

        SQLiteDatabase db = Connector.getDatabase();

    }
}
