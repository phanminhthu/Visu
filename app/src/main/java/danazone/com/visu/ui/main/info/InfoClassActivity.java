package danazone.com.visu.ui.main.info;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.SessionManager;
import danazone.com.visu.common.Common;
import danazone.com.visu.ui.dilaog.TimeDialog;
import danazone.com.visu.ui.main.info.choose.ChooseTutorActivity_;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_info_class)
public class InfoClassActivity extends BaseActivity {
    @ViewById
    EditText mEdtAddressClass;
    @ViewById
    EditText mEdtStudent;
    @ViewById
    TextView mTvDateStart;
    @ViewById
    TextView mTvSumWeek;
    @Extra
    String gender;
    @Extra
    String homeTown;
    @Extra
    String school;
    @Extra
    String subject;
    @Extra
    String mCalss;
    @Extra
    String price;

    private Socket mSocket;

    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();

    }

    @Click({R.id.mTvCancel, R.id.mTvSubmit, R.id.mTv2, R.id.mTv3, R.id.mTv4, R.id.mTv5, R.id.mTv6, R.id.mTv7, R.id.mTv8})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTvCancel:
                //Todo click
                finish();
                break;

            case R.id.mTvSubmit:
                //Todo click
                // ChooseTutorActivity_.intent(this).start();
                JSONObject object = new JSONObject();
                String[] a = new String[]{"\"IA1\"", "\"IA2\""};
                try {
                    object.put("class", "C1");
                    object.put("numberHomeClass", "GGDG");
                    object.put("streetClass", "k11/2");
                    object.put("countyClass", "dn");
                    object.put("cityClass", "44");
                    object.put("numberStudent", 2);
                    object.put("schedule", new JSONArray("[ \"IA1\",\"IA2\",\"IB1\" ]"));
                    object.put("week", 5);
                    object.put("schoolStudent", "gggg");
                    object.put("sexTutor", "M1");
                    object.put("typeTutor", 0);
                    object.put("homeTownTutor", "44");
                    object.put("dateStart", "12/10/2018");
                    object.put("subject", "S1");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mSocket.emit("createTransaction", object);
                ChooseTutorActivity_.intent(InfoClassActivity.this).start();
                break;

            case R.id.mTv2:
                //Todo click

                new TimeDialog(InfoClassActivity.this, new TimeDialog.OnDialogClickListener() {
                    @Override
                    public void onMale() {

                    }

                    @Override
                    public void onFemale() {

                    }
                }).show();
                break;

            case R.id.mTv3:
                //Todo click

                new TimeDialog(InfoClassActivity.this, new TimeDialog.OnDialogClickListener() {
                    @Override
                    public void onMale() {

                    }

                    @Override
                    public void onFemale() {

                    }
                }).show();
                break;

            case R.id.mTv4:
                //Todo click

                break;

            case R.id.mTv5:
                //Todo click
                break;

            case R.id.mTv6:
                //Todo click
                break;

            case R.id.mTv7:
                //Todo click
                break;

            case R.id.mTv8:
                //Todo click
                break;
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mSocket.on("tutorAcceptTransaction", new Emitter.Listener() {
//            @Override
//            public void call(final Object... args) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(InfoClassActivity.this, "" + args[0], Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//        });
//    }
}
