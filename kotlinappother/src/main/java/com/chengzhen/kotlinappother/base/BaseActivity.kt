package com.chengzhen.kotlinappother

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import com.android.tu.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.bar_title.*
import kotlin.properties.Delegates

abstract class BaseActivity : AppCompatActivity() {

    protected var mRegisterEnable : Boolean = false
    protected var mTitleBarEnable = false
    protected lateinit var mActivity: BaseActivity
    private var mLoadingDialog: LoadingDialog? = null
//    protected lateinit var mRlTitle: RelativeLayout
//    protected lateinit var mLlRoot: LinearLayout
//    private lateinit var mIvLeft: ImageView
//    private lateinit var mTvCenterTitle: TextView
//    private lateinit var mIvLeftClose: ImageView
//    private lateinit var mIvRightMore: ImageView
//    protected lateinit var mEtSearch: EditText
//    private var mTvEndMore: TextView by Delegates.notNull()
    protected var mLayoutId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //绑定Mvp
        bindMvp()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mActivity = this
        //应用内事件通讯
        if (mRegisterEnable) EventBus.getDefault().register(this)
        //添加到activity栈
        ActivityManager.instance.addActivity(this)
        //最外层布局
//        mLlRoot = ll_root
        //整个标题栏
//        mRlTitle = rl_title
//        mIvLeft = iv_left_back
//        mTvCenterTitle = tv_center_title
//        mIvLeftClose = iv_left_close
//        mIvRightMore = iv_right_more
//        mEtSearch = et_search
//        mTvEndMore = tv_end_more

        iv_left_back.setOnClickListener { finish() }
        iv_left_close.visibility = View.GONE
        iv_right_more.visibility = View.GONE
        et_search.visibility = View.GONE
        tv_end_more.visibility = View.GONE
        tv_center_title.visibility = View.GONE

        init()
        val vgContent = layoutInflater.inflate(mLayoutId, null)
        ll_root.addView(vgContent, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        if (mTitleBarEnable) rl_title.visibility = View.VISIBLE else rl_title.visibility = View.GONE
        getData()
    }

    protected fun bindMvp() {}

    protected abstract fun init()
    protected abstract fun getData()

    override fun onDestroy() {
        super.onDestroy()

        ActivityManager.instance.removeActivity(this)

        if (mRegisterEnable && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(event: EmptyEvent) {}

    protected fun showProDialogHint() {
        judgeProDialog()
        if (!mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.show()
        }
    }

    protected fun hideProDialogHint() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog!!.isShowing) {
                mLoadingDialog!!.dismiss()
            }
        }
    }

    private fun judgeProDialog(){
        //初始化进度提示框
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.Builder(mActivity)
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .create()
        }
    }

    protected fun setLeftImg(res: Int, listener: View.OnClickListener) {

        iv_left_back.setImageResource(res)
        iv_left_back.setOnClickListener(listener)
    }

    protected fun setLeftClickListener(listener: View.OnClickListener) {

        iv_left_back.setOnClickListener(listener)
    }

    protected fun setCenterTitle(title: String) {

        tv_center_title.text = title
        tv_center_title.visibility = View.VISIBLE
    }

    protected fun setCenterTitle(title: CharSequence) {
        tv_center_title.text = title
        tv_center_title.visibility = View.VISIBLE
    }

    protected fun setCloseClickListener(listener: View.OnClickListener) {

        iv_left_close.visibility = View.VISIBLE
        iv_left_close.setOnClickListener(listener)
    }

    protected fun setMoreClickListener(listener: View.OnClickListener) {

        iv_right_more.visibility = View.VISIBLE
        iv_right_more.setOnClickListener(listener)
    }


    protected fun setSearchListener(watcher: ATextTWatcher, listener: View.OnClickListener) {

        et_search.addTextChangedListener(watcher)
        setSearchListener(listener)
    }

    protected fun setSearchListener(listener: View.OnClickListener) {

        et_search.visibility = View.VISIBLE
        tv_end_more.setOnClickListener(listener)
        tv_end_more.visibility = View.VISIBLE
    }
}
