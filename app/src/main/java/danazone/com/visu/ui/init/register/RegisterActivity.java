package danazone.com.visu.ui.init.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.VisuAplication;
import danazone.com.visu.common.Common;
import danazone.com.visu.ui.init.login.LoginActivity_;
import danazone.com.visu.ui.main.MainActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewById
    EditText mEdtPhone;
    @ViewById
    EditText mEdtName;
    @ViewById
    EditText mEdtAddress;
    @ViewById
    EditText mEdtPass;
    @ViewById
    EditText mEdtNumberHouse;
    @ViewById
    EditText mEdtCounty;
    @ViewById
    EditText mEdtProvince;

    private Socket mSocket;


    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();
        mSocket.connect();
    }

    /**
     * Event click cancel and submit register
     *
     * @param v
     */
    @Click({R.id.mTvCancel, R.id.mTvRegister, R.id.mTvLogin})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvCancel:
                finish();
                break;

            case R.id.mTvRegister:
                //Todo RegisterActivity click mTvRegister
                if (mEdtName.getText().toString().trim().length() == 0) {
                    showAlertDialog("Vui lòng nhập họ tên");
                    mEdtName.requestFocus();
                    return;
                }
                if (mEdtPass.getText().toString().trim().length() == 0) {
                    showAlertDialog("Vui lòng nhập mật khẩu");
                    mEdtPass.requestFocus();
                    return;
                }

                if (mEdtPass.getText().toString().trim().length() < 6) {
                    showAlertDialog("Mật khẩu phải từ 6 ký tự trở lên");
                    mEdtPass.requestFocus();
                    return;
                }
                if (mEdtPhone.getText().toString().trim().length() == 0) {
                    showAlertDialog("Số điện thoại không được để trống");
                    mEdtPhone.requestFocus();
                    return;
                }
                if (mEdtPhone.getText().toString().trim().length() <= 10) {
                    showAlertDialog("Số điện thoại gồm 11 hoặc 12 số");
                    mEdtPhone.requestFocus();
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

                JSONObject object = new JSONObject();
                try {
                    object.put("name", mEdtName.getText().toString());
                    object.put("pass", mEdtPass.getText().toString());
                    object.put("phoneNumber", mEdtPhone.getText().toString());
                    object.put("homeNumber", mEdtNumberHouse.getText().toString());
                    object.put("street", mEdtAddress.getText().toString());
                    object.put("county", mEdtCounty.getText().toString());
                    object.put("city", mEdtProvince.getText().toString());
                    object.put("species", 0);
                    object.put("type", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("register", object);
                mSocket.on("register", onRegister);

                break;

            case R.id.mTvLogin:
                LoginActivity_.intent(this).start();
                break;
        }
    }

    private Emitter.Listener onRegister = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (args[0].toString().matches("true")) {
                        System.out.println("666666666666666666666666666667777777" + args[1].toString());
                        MainActivity_.intent(RegisterActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        finish();
                    } else {
                        showAlertDialog("" + args[1].toString());
                    }
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.close();
    }
}
