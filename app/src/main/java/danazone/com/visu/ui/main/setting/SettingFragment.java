package danazone.com.visu.ui.main.setting;

import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import danazone.com.visu.BaseContainerFragment;
import danazone.com.visu.R;
import danazone.com.visu.ui.main.setting.account.AccountActivity_;
import danazone.com.visu.ui.main.setting.account.update.UpdateAccountActivity_;

@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseContainerFragment {
    @Override
    protected void afterViews() {
    }

    @Click({R.id.mLlAccount})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLlAccount:
                AccountActivity_.intent(this).start();
                break;
        }
    }
}
