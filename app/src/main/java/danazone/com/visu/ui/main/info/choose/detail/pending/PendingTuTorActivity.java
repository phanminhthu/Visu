package danazone.com.visu.ui.main.info.choose.detail.pending;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.R;
import danazone.com.visu.bean.Users;

@SuppressLint("Registered")
@EActivity(R.layout.activity_pending_tutor)
public class PendingTuTorActivity extends BaseActivity {
    @ViewById
    TextView mTvTime;

    @Override
    protected void afterView() {
         startTimer();
    }

    private void startTimer() {
        // 5 Minutes
        long totalTimeCountInMilliseconds = 60 * 5 * 1000;

        new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                String timer = String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60);
                mTvTime.setText("Thời gian còn lại: " + timer);
            }

            @Override
            public void onFinish() {
            }

        }.start();
    }
}
