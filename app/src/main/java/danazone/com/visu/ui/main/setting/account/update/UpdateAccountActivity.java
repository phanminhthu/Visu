package danazone.com.visu.ui.main.setting.account.update;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.sqlite.DBManager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_update_account)
public class UpdateAccountActivity extends BaseActivity {
    @ViewById
    EditText mEdtName;
    @ViewById
    TextView mTvPhone;
    @ViewById
    EditText mEdtHomeNumber;
    @ViewById
    EditText mEdtStreet;
    @ViewById
    EditText mEdtEmail;
    @ViewById
    EditText mEdtCountry;
    @ViewById
    EditText mEdtCity;

    private Socket mSocket;
    private DBManager dbManager;

    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        dbManager = new DBManager(this);
        Cursor cursor = dbManager.getData();
        while (cursor.moveToNext()) {
            mEdtName.setText(cursor.getString(1));
            mTvPhone.setText(cursor.getString(2));
            mEdtHomeNumber.setText(cursor.getString(3));
            mEdtStreet.setText(cursor.getString(4));
            mEdtCity.setText(cursor.getString(5));

            mEdtCountry.setText(cursor.getString(6));
            if (cursor.getString(8) != null) {
                mEdtEmail.setText(cursor.getString(8));
            }
        }
    }

    @Click({R.id.mImgAvatar, R.id.mTvSubmit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvSubmit:
                JSONObject object = new JSONObject();
                try {
                    object.put("name", "");
                    object.put("homeNumber", mEdtHomeNumber.getText().toString());
                    object.put("street", mEdtStreet.getText().toString());
                    object.put("city", mEdtCity.getText().toString());
                    object.put("county", mEdtCountry.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mSocket.emit("updateInfomation", object);
                mSocket.on("updateInfomation", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (args[0].toString().matches("true")) {
                                    Toast.makeText(UpdateAccountActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(UpdateAccountActivity.this, "hhhhhhhh", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                break;

            case R.id.mImgAvatar:

                break;
        }
    }
}
