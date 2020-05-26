package com.yvrun.officeprocess.core.primary.user;


import com.ruffian.library.widget.REditText;
import com.ruffian.library.widget.RTextView;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.mvp.view.BaseMvpFragment;

import butterknife.BindView;

public class LoginFragment extends BaseMvpFragment {

    @BindView(R.id.et_account)
    REditText mEtAccount;
    @BindView(R.id.et_password)
    REditText mEtPassword;
    @BindView(R.id.tv_login)
    RTextView mTvLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {

    }

}
