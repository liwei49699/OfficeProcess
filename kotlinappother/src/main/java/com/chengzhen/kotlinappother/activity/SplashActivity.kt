package com.chengzhen.kotlinappother

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override fun init() {

        mLayoutId = R.layout.activity_splash
        ImmersionBar.with(this)
                //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .fullScreen(true)
                .init()
    }

    override fun getData() {

        rl_content.startAnimation(AlphaAnimation(0.1F,1.0F)
                .apply {
                    duration = 3000L
                    setAnimationListener(object : Animation.AnimationListener{
                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            startActivity<MainActivity>()
//                            finish()
                        }

                        override fun onAnimationStart(animation: Animation?) {
                        }

                    })
                }
        )
    }
}
