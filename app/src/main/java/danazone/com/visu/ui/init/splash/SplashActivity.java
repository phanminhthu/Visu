package danazone.com.visu.ui.init.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.R;
import danazone.com.visu.ui.init.login.LoginActivity_;
import danazone.com.visu.ui.init.register.RegisterActivity_;
import danazone.com.visu.ui.main.MainActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {
    @ViewById
    TextView mTvSplash;
    private Handler mHandler = new Handler();

    @Override
    protected void afterView() {
        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(400);
        mAnimation.setRepeatCount(android.view.animation.Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mTvSplash.startAnimation(mAnimation);

        Runnable mActivityStarter = new Runnable() {
            @Override
            public void run() {
                LoginActivity_.intent(SplashActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK).start();
                finish();
            }
        };
        mHandler.postDelayed(mActivityStarter, 2000);
    }
}
