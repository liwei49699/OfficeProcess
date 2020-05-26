package com.chengzhen.kotlinappother

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import android.util.TypedValue.COMPLEX_UNIT_SP
import androidx.fragment.app.FragmentActivity
import com.android.tu.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.bar_fragment_title.*
import kotlinx.android.synthetic.main.fragment_base.*

abstract class BaseFragment : Fragment() {

    protected var mTAG = javaClass.simpleName
    protected  var mActivity: FragmentActivity? = null
    protected lateinit var mFragment: BaseFragment
    private var mLoadingDialog: LoadingDialog? = null
    protected var mRegisterEnable : Boolean = false
    private var mRootView: View? = null
    protected var mLayoutId = 0
    private var mFirstLoad = true
    protected var mTitleBarEnable = false

    override fun onAttach(context: Context) {
        bindMvp()
        super.onAttach(context)

        Log.d("--TAG--", "onAttach: $mTAG")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity
        mFragment = this
        if (mRegisterEnable) EventBus.getDefault().register(this)

        Log.d("--TAG--", "onCreate: $mTAG")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_base, container, false)
        }
        return mRootView
    }

    protected fun bindText(): String {

        return "默认文本"
    }

    private fun bindView(msg: String): View {

        val relativeLayout = RelativeLayout(mActivity)
        val textView = TextView(mActivity)
        textView.text = msg
        textView.setTextSize(COMPLEX_UNIT_SP, 20f)
        relativeLayout.addView(textView)


        val layoutParams = textView.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        textView.gravity = Gravity.CENTER
        textView.layoutParams = layoutParams
        return relativeLayout
    }


    /**
     * 强制子类重写，实现子类特有的ui
     * @return
     */
    protected abstract fun initView()

    protected abstract fun getData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        ll_fragment_root.apply {
            addView(
                if(mLayoutId == 0) bindView(bindText())
                else layoutInflater.inflate(mLayoutId, ll_fragment_root, false)
                ,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            )
        }
        iv_fragment_more.visibility = View.GONE
        if (mTitleBarEnable) {
            rl_fragment_title.visibility = View.VISIBLE
        } else {
            rl_fragment_title.visibility = View.GONE
        }

        Log.d("--TAG--", "onViewCreated: $mTAG")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("--TAG--", "onActivityCreated: $mTAG")
    }


    protected fun bindMvp() {

    }

    override fun onStart() {
        super.onStart()

        Log.d("--TAG--", "onStart: $mTAG")
    }

    override fun onResume() {
        super.onResume()

        if (mFirstLoad) {

//            initView()
            getData()
            mFirstLoad = false
        }
        Log.d("--TAG--", "onResume: $mTAG")
    }

    override fun onPause() {
        super.onPause()

        Log.d("--TAG--", "onPause: $mTAG")
    }

    override fun onStop() {
        super.onStop()

        Log.d("--TAG--", "onStop: $mTAG")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //        mRootView = null;
        Log.d("--TAG--", "onDestroyView: $mTAG")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(event: EmptyEvent) {

    }

    override fun onDestroy() {
        super.onDestroy()

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }

        Log.d("--TAG--", "onDestroy: $mTAG")
    }

    protected fun showProDialogHint() {
        judgeProDialog()
        if (!mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.show()
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

    protected fun hideProDialogHint() {
        judgeProDialog()
        if (mLoadingDialog != null) {
            if (mLoadingDialog!!.isShowing) {
                mLoadingDialog!!.dismiss()
            }
        }
    }

    protected fun setCenterTitle(title: String) {
        tv_fragment_title!!.text = title
    }

    protected fun setCenterTitle(title: CharSequence) {
        tv_fragment_title!!.text = title
    }

    protected fun setMoreClickListener(@DrawableRes resId: Int, listener: View.OnClickListener) {

        iv_fragment_more.visibility = View.VISIBLE
        iv_fragment_more.setImageResource(resId)
        iv_fragment_more.setOnClickListener(listener)
    }
}
