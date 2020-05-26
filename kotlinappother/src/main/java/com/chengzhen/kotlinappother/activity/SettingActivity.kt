package com.chengzhen.kotlinappother.activity

import android.view.View

import com.chengzhen.kotlinappother.R
import com.chengzhen.kotlinappother.mvp.contract.SettingContract
import com.chengzhen.kotlinappother.mvp.view.BaseMvpActivity


class SettingActivity : BaseMvpActivity<SettingContract.SettingPresenterImp>(), SettingContract.ISettingView {

    override fun init() {

        mLayoutId = R.layout.activity_setting
        setCenterTitle("设置")
    }

    override fun getData() {


    }

    @OnClick(R.id.ll_about, R.id.ll_clear_cache, R.id.rtv_logout)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.ll_about -> {
            }
            R.id.ll_clear_cache -> {
            }
            R.id.rtv_logout ->

                showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {

        MessageDialog.build(this)
                .setStyle(DialogSettings.STYLE.STYLE_MATERIAL)
                .setTheme(DialogSettings.THEME.LIGHT)
                .setTitle("提示消息")
                .setMessage("确定要退出当前账户吗？")
                .setOkButton("确定", object : OnDialogButtonClickListener() {
                    fun onClick(baseDialog: BaseDialog, v: View): Boolean {

                        logout()
                        return false
                    }
                })
                .setCancelButton("取消")
                .show()
    }

    private fun logout() {

        mPresenter.getLogout()
    }

    fun getLogout(BaseRspBean: BaseRspBean) {

        UserUtils.getInstance().logout()
        LoginEvent(false).post()
        finish()
    }
}
