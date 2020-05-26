package com.chengzhen.kotlinappother.adapter

import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chengzhen.kotlinappother.R
import com.chengzhen.kotlinappother.base.GlideApp
import com.chengzhen.kotlinappother.data.ArticleResponseBean

class HomeAdapter : BaseQuickAdapter<ArticleResponseBean.DataBean.DatasBean, BaseViewHolder>(R.layout.item_home) {

    private var mOnCollectViewClickListener: OnCollectViewClickListener? = null

    override fun convert(helper: BaseViewHolder, item: ArticleResponseBean.DataBean.DatasBean) {
        helper.setText(R.id.tv_title, Html.fromHtml(item.title))
                .setText(R.id.tv_author, item.author)
                .setText(R.id.tv_time, item.niceDate)
                .setText(R.id.tv_super_chapter_name, item.superChapterName)
                .setText(R.id.tv_chapter_name, item.chapterName)
                .setGone(R.id.ll_new, item.isFresh)

        val iv_img = helper.getView<ImageView>(R.id.iv_img)
        if (!TextUtils.isEmpty(item.envelopePic)) {
            GlideApp.with(iv_img.context).load(item.envelopePic).into(iv_img)
            iv_img.visibility = View.VISIBLE
        } else {
            iv_img.visibility = View.GONE
        }
        val cb_collect = helper.getView<CheckBox>(R.id.cb_collect)
        if (item.isCollect) {
            cb_collect.isChecked = true
        } else {
            cb_collect.isChecked = false
        }
        val tv_tag = helper.getView<TextView>(R.id.tv_tag)
        if (item.tags != null && item.tags.size > 0) {
            tv_tag.text = item.tags[0].name
            tv_tag.visibility = View.VISIBLE
        } else {
            tv_tag.visibility = View.GONE
        }

        cb_collect.setOnCheckedChangeListener { buttonView, isChecked ->
            if (mOnCollectViewClickListener != null) {
                mOnCollectViewClickListener!!.onClick(helper, buttonView, helper.adapterPosition - headerLayoutCount)
            }
        }
    }

    interface OnCollectViewClickListener {
        fun onClick(helper: BaseViewHolder, v: CompoundButton, position: Int)
    }

    fun setOnCollectViewClickListener(listener: OnCollectViewClickListener) {

        mOnCollectViewClickListener = listener
    }
}
