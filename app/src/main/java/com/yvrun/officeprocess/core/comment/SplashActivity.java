package com.yvrun.officeprocess.core.comment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;

import com.airbnb.lottie.LottieAnimationView;
import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.core.primary.main.MainActivity;
import com.yvrun.officeprocess.mvp.view.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.lav_splash)
    LottieAnimationView mLavSplash;

    @Override
    protected int getLayoutID() {

        return R.layout.activity_splash;
    }

    @Override
    protected void init() {

        initAnimal();
    }

    @Override
    protected void getData() {

    }

    private void initAnimal() {

        mLavSplash.setAnimation("splash.json");
//        mLavSplash.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Overrideao(1).json
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                Log.d("--TAG--" + Thread.currentThread().getName(), "onAnimationUpdate: " + animation.getDuration());
//                Log.d("--TAG--" + Thread.currentThread().getName(), "onAnimationUpdate: " + animation.getAnimatedValue());
//
////                mLavSplash.setProgress(0.5f);
//
//            }
//        });

        mLavSplash.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                jumpHome();
            }
        });
//        播放次数
//        mLavSplash.setRepeatCount(-1);
//        倒放及速度
//        mLavSplash.setSpeed(-1);
//        播放间断
//        mLavSplash.setMinProgress(0.3F);
//        mLavSplash.setMaxProgress(0.6F);
        mLavSplash.playAnimation();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {

        if(mLavSplash != null) {
            mLavSplash.cancelAnimation();
        }
        super.onDestroy();
    }

    public void jumpHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
