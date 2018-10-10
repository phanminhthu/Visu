package danazone.com.visu.ui.main.select;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.R;
import danazone.com.visu.RecyclerViewUtils;

@SuppressLint("Registered")
@EActivity(R.layout.activity_select)
public class SelectActivity extends BaseActivity {
    @ViewById
    RecyclerView mRecycleView;
    @ViewById
    EditText mEdtSearch;
    @ViewById
    TextView mTvTitle;

    @Extra
    int mKeySelect;

    private SelectAdapter mAdapter;

    @Override
    protected void afterView() {
        if (mKeySelect == 1) {
            mTvTitle.setText("Chọn môn học");
        } else if (mKeySelect == 2) {
            mTvTitle.setText("Chọn lớp học");
        } else if (mKeySelect == 3) {
            mTvTitle.setText("Chọn giá");
        }

        setUpAdapter();
    }

    private void setUpAdapter() {
        RecyclerViewUtils.Create().setUpVertical(this, mRecycleView);
        mAdapter = new SelectAdapter(this, new SelectAdapter.OnSelectClickListener() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent("select");
                if(mKeySelect ==1) {
                    intent.putExtra("message", "Toán");
                    intent.putExtra("key","1");
                }else if(mKeySelect == 2){
                    intent.putExtra("message", "Toán");
                    intent.putExtra("key","2");
                }else if(mKeySelect == 3){
                    intent.putExtra("message", "Toán");
                    intent.putExtra("key","3");
                }
                LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
                finish();
            }
        });
        mRecycleView.setAdapter(mAdapter);
    }

}
