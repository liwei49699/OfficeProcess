package com.chengzhen.kotlinappother

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chengzhen.kotlinappother.fragment.*
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView


class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("首页", "体系", "公众号", "项目", "我的")
    private val mSelectIcons = intArrayOf(R.drawable.ic_home_selected,
            R.drawable.ic_book_selected, R.drawable.ic_wechat_selected,
            R.drawable.ic_project_selected, R.drawable.ic_mine_selected)
    private val mNormalIcons = intArrayOf(R.drawable.ic_home_normal,
            R.drawable.ic_book_normal, R.drawable.ic_wechat_normal,
            R.drawable.ic_project_normal, R.drawable.ic_mine_normal)

    override fun init() {
        mLayoutId = R.layout.activity_main
        //状态栏颜色，不写默认透明色
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .init()

    }

    override fun getData() {

        initViewpager()
        initIndicator()
    }

    private fun initViewpager() {

        val homeFragment = HomeFragment()
        val knowledgeFragment = KnowledgeFragment()
        val weChatFragment = WeChatFragment()
        val projectFragment = ProjectFragment()
        val mineFragment = MineFragment()
        val fragmentList = arrayOf(homeFragment, knowledgeFragment, weChatFragment, projectFragment, mineFragment)

        vp_main.offscreenPageLimit = 4
        vp_main.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
    }


    private fun initIndicator() {

        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {

            override fun getCount(): Int {
                return mTitles.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)

                val customLayout = LayoutInflater.from(context).inflate(R.layout.item_indicator_bottom, null)
                val titleImg = customLayout.findViewById<ImageView>(R.id.title_img)
                val titleText = customLayout.findViewById<TextView>(R.id.title_text)
                titleText.text = mTitles[index]
                commonPagerTitleView.setContentView(customLayout)

                commonPagerTitleView.onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {

                    override fun onSelected(index: Int, totalCount: Int) {
                        titleText.setTextColor(resources.getColor(R.color.colorPrimary))
                        titleImg.setImageResource(mSelectIcons[index])

                    }

                    override fun onDeselected(index: Int, totalCount: Int) {
                        titleText.setTextColor(resources.getColor(R.color.colorTvNormal))
                        titleImg.setImageResource(mNormalIcons[index])
                    }

                    override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
                        titleImg.scaleX = 1.3f + (1.0f - 1.3f) * leavePercent
                        titleImg.scaleY = 1.3f + (1.0f - 1.3f) * leavePercent
                    }

                    override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
                        titleImg.scaleX = 1.0f + (1.3f - 1.0f) * enterPercent
                        titleImg.scaleY = 1.0f + (1.3f - 1.0f) * enterPercent
                    }
                }

                commonPagerTitleView.setOnClickListener { vp_main.currentItem = index }

                return commonPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                return null
            }
        }
        mi_bottom_nav.navigator = commonNavigator
        ViewPager2Helper.bind(mi_bottom_nav, vp_main)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
