package danazone.com.visu.ui.main.info.choose.detail;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.SessionManager;
import danazone.com.visu.bean.Users;
import danazone.com.visu.sqlite.DBManager;
import danazone.com.visu.ui.main.info.choose.detail.pending.PendingTuTorActivity_;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_detail_tutor)
public class DetailTutorActivity extends BaseActivity {
    @ViewById
    TextView mTvTime;
    @Extra
    Users users;

    @ViewById
    TextView mTvName;
    @ViewById
    TextView mTvSchool;
    @ViewById
    TextView mTvBirthday;
    @ViewById
    TextView mTvAddress;
    @ViewById
    TextView mTvStatus;
    @ViewById
    TextView mTvExperience;
    @ViewById
    TextView mTvGender;
    @ViewById
    TextView mEdtPhone;

    private DBManager dbManager;
    private Socket mSocket;
    private String id;


    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

        startTimer();
        if (users != null) {
            mTvName.setText(users.getName());
            mTvSchool.setText(users.getSchool());
            mTvBirthday.setText(users.getBirthday());
            mTvAddress.setText(users.getHomeTown());
            if (users.getGender().equals("M1")) {
                mTvGender.setText("Nam");
            } else {
                mTvGender.setText("Nữ");
            }
            mTvStatus.setText(users.getStatus());
            mTvExperience.setText(users.getExperience() + " năm");
            id = String.valueOf(users.getId());
            System.out.println("5555555555666666666666666666666666666666666666: " + id);

            dbManager = new DBManager(this);
            Cursor cursor = dbManager.getData();
            while (cursor.moveToNext()) {
                mEdtPhone.setText(cursor.getString(2));
            }
        }
    }

    @Click({R.id.mTvSubmit, R.id.mTvPhoneSubmit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvSubmit:
                Toast.makeText(DetailTutorActivity.this,"hhhhhh", Toast.LENGTH_SHORT).show();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("idTutor", "41");
                    jsonObject.put("phoneNumberTransactionParent", mEdtPhone.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mSocket.emit("acceptTutor", jsonObject);
                mSocket.on("acceptTutor", new Emitter.Listener() {
                    @Override
                    public void call(final Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(args[0].toString().matches("false")){
                                    showAlertDialog("" + args[1]);
                                }
                            }
                        });
                    }
                });
                //PendingTuTorActivity_.intent(this).start();
                break;

            case R.id.mTvPhoneSubmit:

                break;
        }

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
