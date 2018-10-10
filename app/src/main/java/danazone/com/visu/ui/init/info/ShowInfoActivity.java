package danazone.com.visu.ui.init.info;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.R;
import danazone.com.visu.ui.main.MainActivity_;

@SuppressLint("Registered")
@EActivity(R.layout.activity_show_info)
public class ShowInfoActivity extends BaseActivity {

    @ViewById
    EditText mEdtName;
    @ViewById
    EditText mEdtAddress;
    @ViewById
    EditText mEdtNumberHouse;
    @ViewById
    EditText mEdtCounty;
    @ViewById
    EditText mEdtProvince;

    @Extra
    String mName;
    @Extra
    String mToken;

    @Override
    protected void afterView() {

    }

    @Click(R.id.mTvNext)
    void onClick(View v) {
        if (mEdtName.getText().toString().trim().length() == 0) {
            showAlertDialog("Vui lòng nhập họ tên");
            mEdtName.requestFocus();
            return;
        }
        if (mEdtNumberHouse.getText().toString().trim().length() == 0) {
            showAlertDialog("Vui lòng nhập số nhà");
            mEdtNumberHouse.requestFocus();
            return;
        }

        if (mEdtAddress.getText().toString().trim().length() == 0) {
            showAlertDialog("Vui lòng nhập tên đường");
            mEdtAddress.requestFocus();
            return;
        }
        if (mEdtCounty.getText().toString().trim().length() == 0) {
            showAlertDialog("Vui lòng nhập tên quận/huyện");
            mEdtCounty.requestFocus();
            return;
        }
        if (mEdtProvince.getText().toString().trim().length() == 0) {
            showAlertDialog("Vui lòng nhập tên thành phố");
            mEdtProvince.requestFocus();
            return;
        }
        MainActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK).start();
        finish();
    }
}
