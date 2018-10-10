package danazone.com.visu.ui.main.teacher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import danazone.com.visu.BaseContainerFragment;
import danazone.com.visu.R;
import danazone.com.visu.ui.main.info.InfoClassActivity_;
import danazone.com.visu.ui.main.select.SelectActivity_;

@EFragment(R.layout.teacher_fragment)
public class TeacherFragment extends BaseContainerFragment {
    @ViewById
    TextView mTvStatus;
    @ViewById
    TextView mTvClass;
    @ViewById
    TextView mTvPrice;

    @Override
    protected void afterViews() {
        LocalBroadcastManager.getInstance(getContext()).registerReceiver((receiver),
                new IntentFilter("select"));
    }

    @Click({R.id.mTvStatus, R.id.mTvClass, R.id.mTvPrice, R.id.mTvRegister})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvStatus:
                //Todo click

                SelectActivity_.intent(this)
                        .mKeySelect(1)
                        .start();
                break;

            case R.id.mTvClass:
                //Todo click

                SelectActivity_.intent(this)
                        .mKeySelect(2)
                        .start();
                break;

            case R.id.mTvPrice:
                //Todo click

                SelectActivity_.intent(this)
                        .mKeySelect(3)
                        .start();
                break;

            case R.id.mTvRegister:
                InfoClassActivity_.intent(this).start();
                break;
        }
    }

    // Broadcast accept
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {

                String mMessage = intent.getStringExtra("message");
                String mKey = intent.getStringExtra("key");
                if (mMessage != null) {
                    if (mKey.equals("1")) {
                        mTvStatus.setText(mMessage);
                    } else if (mKey.equals("2")) {
                        mTvClass.setText(mMessage);
                    } else if (mKey.equals("3")) {
                        mTvPrice.setText(mMessage);
                    }
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
        super.onDestroyView();
    }
}
