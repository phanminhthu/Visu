package danazone.com.visu.ui.main.info.choose;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseApp;
import danazone.com.visu.R;
import danazone.com.visu.RecyclerViewUtils;
import danazone.com.visu.bean.Users;
import danazone.com.visu.ui.main.info.choose.detail.DetailTutorActivity_;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
@EActivity(R.layout.activity_choose_tutor)
public class ChooseTutorActivity extends BaseActivity {
    private Socket mSocket;
    @ViewById
    RecyclerView mRecycleView;
    private ChooseTuTorAdapter mAdapter;
    private List<Users> mListTuTo;

    @Override
    protected void afterView() {
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();
        mListTuTo = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpAdapter();
        mSocket.on("tutorAcceptTransaction", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Users chooseTuTo = new Users();
                        JSONObject jsonObject = (JSONObject) args[0];
                        try {
                            chooseTuTo.setId(Integer.valueOf(jsonObject.getString("id")));
                            chooseTuTo.setName(jsonObject.getString("name"));
                            chooseTuTo.setId(jsonObject.getInt("type"));
                            chooseTuTo.setGender(jsonObject.getString("sex"));
                            chooseTuTo.setBirthday(jsonObject.getString("yearOfBirth"));
                            chooseTuTo.setSchool(jsonObject.getString("school"));
                            chooseTuTo.setHomeTown(jsonObject.getString("homeTown"));
                            chooseTuTo.setPoint(Integer.valueOf(jsonObject.getString("point")));
                            chooseTuTo.setExperience(Integer.valueOf(jsonObject.getString("experience")));
                            chooseTuTo.setStatus(jsonObject.getString("status"));
                            chooseTuTo.setIdTransaction(jsonObject.getInt("totalTransaction"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mListTuTo.add(chooseTuTo);
                        mAdapter.notifyDataSetChanged();
                        System.out.println("888888888888888888888888888888888888 : " + mListTuTo.size());
                    }
                });

            }

        });
    }

    private void setUpAdapter() {
        RecyclerViewUtils.Create().setUpVertical(this, mRecycleView);
        mAdapter = new ChooseTuTorAdapter(this, mListTuTo, new ChooseTuTorAdapter.OnSelectClickListener() {
            @Override
            public void onClickItem(Users position) {
                //Todo intent
                DetailTutorActivity_.intent(ChooseTutorActivity.this).users(position).start();
            }
        });
        mRecycleView.setAdapter(mAdapter);
    }
}
