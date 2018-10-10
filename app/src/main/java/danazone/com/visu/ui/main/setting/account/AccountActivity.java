package danazone.com.visu.ui.main.setting.account;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.view.View;
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
import danazone.com.visu.common.Common;
import danazone.com.visu.sqlite.DBManager;
import danazone.com.visu.ui.main.setting.account.update.UpdateAccountActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_account)
public class AccountActivity extends BaseActivity {
    @ViewById
    TextView mTvName;
    @ViewById
    TextView mTvPhone;
    @ViewById
    TextView mTvHomeNumber;
    @ViewById
    TextView mTvStreet;
    @ViewById
    TextView mTvEmail;
    @ViewById
    TextView mTvCountry;
    @ViewById
    TextView mTvCity;

    private Socket mSocket;
    private DBManager dbManager;

    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        JSONObject jsonObject = new JSONObject();


        mSocket.on("getInfomation", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AccountActivity.this, "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv" + args[1], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dbManager = new DBManager(this);
        Cursor cursor = dbManager.getData();
        while (cursor.moveToNext()) {
            mTvName.setText(cursor.getString(1));
            mTvPhone.setText(cursor.getString(2));
            mTvHomeNumber.setText(cursor.getString(3));
            mTvStreet.setText(cursor.getString(4));
            mTvCountry.setText(cursor.getString(5));
            mTvCity.setText(cursor.getString(6));
            if (cursor.getString(8) != null) {
                mTvEmail.setText(cursor.getString(8));
            }
        }
    }

    @Click(R.id.mTvSubmit)
    void onClick(View v) {
        UpdateAccountActivity_.intent(this).start();
    }
}
