package danazone.com.visu.ui.init.login;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.SessionManager;
import danazone.com.visu.bean.InfoUser;
import danazone.com.visu.common.Common;
import danazone.com.visu.service.VisuService;
import danazone.com.visu.sqlite.DBManager;
import danazone.com.visu.ui.init.info.ShowInfoActivity_;
import danazone.com.visu.ui.init.register.RegisterActivity;
import danazone.com.visu.ui.init.register.RegisterActivity_;
import danazone.com.visu.ui.main.MainActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewById
    EditText mEdtPassWord;
    @ViewById
    EditText mEdtPhone;
    private Socket mSocket;
    private DBManager dbManager;


    private Intent mServiceIntent;
    private VisuService mSensorService;
    private Context ctx;

    public Context getCtx() {
        return ctx;
    }
    @Override
    protected void afterView() {
        ctx = this;
        mSensorService = new VisuService(getCtx());
        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);

        }

        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        dbManager = new DBManager(this);
    }

    @Click({R.id.mTvLogin, R.id.mTvRegister, R.id.mTvGoogle, R.id.mTvFacebook})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvLogin:
                // Todo mTvLogin click
                JSONObject object = new JSONObject();
                try {
                    if (!SessionManager.getInstance().getKeySavePhone().equals("") && !SessionManager.getInstance().getKeySavePass().equals("")) {
                        object.put("phoneNumber", SessionManager.getInstance().getKeySavePhone());
                        object.put("pass", SessionManager.getInstance().getKeySavePass());
                    } else {
                        object.put("phoneNumber", mEdtPhone.getText().toString());
                        object.put("pass", mEdtPassWord.getText().toString());
                    }
                    object.put("species", 0);
                    object.put("type", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("login", object);
                mSocket.on("login", onLogin);

                break;

            case R.id.mTvRegister:
                RegisterActivity_.intent(this).start();
                finish();
                break;

            case R.id.mTvGoogle:
                // Todo mTvGoogle click

                ShowInfoActivity_.intent(this).start();
                break;

            case R.id.mTvFacebook:
                // Todo mTvFacebook click

                ShowInfoActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK).start();
                finish();
                break;
        }
    }

    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (args[0].toString().matches("true")) {
                        //Toast.makeText(LoginActivity.this, "" + args[1].toString(), Toast.LENGTH_SHORT).show();
                        System.out.println("666666666666666666666666666667777777" + args[1].toString());
                        if (SessionManager.getInstance().getKeySavePhone().equals("") && SessionManager.getInstance().getKeySavePass().equals("")) {
                            SessionManager.getInstance().setKeySavePhone(mEdtPhone.getText().toString().trim());
                            SessionManager.getInstance().setKeySavePass(mEdtPassWord.getText().toString().trim());
                        }


                        JSONObject jsonObject = (JSONObject) args[1];
                        try {
                            String id = jsonObject.getString("id");
                            String name = jsonObject.getString("name");
                            String phone = jsonObject.getString("phoneNumber");
                            String homeNumber = jsonObject.getString("homeNumber");
                            String street = jsonObject.getString("street");
                            String country = jsonObject.getString("county");
                            String city = jsonObject.getString("city");
                            String type = jsonObject.getString("type");
                            String email = jsonObject.getString("email");
                            String avatar = jsonObject.getString("avatar");
                            String idSoket = jsonObject.getString("idSocket");
                            InfoUser infoUser = new InfoUser();
                            infoUser.setName(name);
                            infoUser.setPhone(phone);
                            infoUser.setHomeNumber(homeNumber);
                            infoUser.setStreet(street);
                            infoUser.setCountry(country);
                            infoUser.setCity(city);
                            infoUser.setType(Integer.valueOf(type));
                            infoUser.setEmail(email);
                            infoUser.setAvatar(avatar);
                            dbManager.addIfoUser(infoUser);
                            SessionManager.getInstance().setKeySaveIdSocket(idSoket);
                            SessionManager.getInstance().setKeySaveID(id);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        MainActivity_.intent(LoginActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP
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

        private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }
}
