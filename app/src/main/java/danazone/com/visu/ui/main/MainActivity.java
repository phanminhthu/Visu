package danazone.com.visu.ui.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import danazone.com.visu.BaseActivity;
import danazone.com.visu.BaseContainerFragment;
import danazone.com.visu.BaseFragment;
import danazone.com.visu.R;
import danazone.com.visu.service.VisuService;
import danazone.com.visu.sqlite.DBManager;
import danazone.com.visu.ui.main.setting.SettingFragment;
import danazone.com.visu.ui.main.setting.SettingFragment_;
import danazone.com.visu.ui.main.teacher.TeacherFragment;
import danazone.com.visu.ui.main.teacher.TeacherFragment_;
import danazone.com.visu.ui.main.tutor.TutorFragment;
import danazone.com.visu.ui.main.tutor.TutorFragment_;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private Toast mToastExit;
    @ViewById
    BottomNavigationView mView;
    @ViewById
    TextView mTvLogo;
    private Intent mServiceIntent;
    private VisuService mSensorService;
    private Context ctx;

    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void afterView() {
        getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_PAN);
        ctx = this;
        mToastExit = Toast.makeText(MainActivity.this, getResources().getString(R.string.text_back_exit), Toast.LENGTH_SHORT);
        replaceFragment(TutorFragment_.builder().build());
        viewListener();
//        mSensorService = new VisuService(getCtx());
//        mServiceIntent = new Intent(getCtx(), mSensorService.getClass());
//        if (!isMyServiceRunning(mSensorService.getClass())) {
//            startService(mServiceIntent);
//            System.out.println("4444444444444444444444444444444444444");
//        }
    }

    /**
     * Open new fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransactionAccount = getSupportFragmentManager().beginTransaction();
        fragmentTransactionAccount.replace(R.id.mFrameContainer, fragment);
        fragmentTransactionAccount.commit();
    }

    /**
     * check current fragment id
     *
     * @return
     */
    public BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.mFrameContainer);
    }

    @Override
    public void onBackPressed() {
        BaseContainerFragment fragment = (BaseContainerFragment) getCurrentFragment();

        if (!fragment.popFragment()) {
            boolean isExit = mToastExit.getView().isShown();
            if (!isExit) {
                mToastExit.show();
            } else {
                super.onBackPressed();
                System.exit(0);
            }
        }
    }

    private void viewListener() {
        mView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.mTutor:
                        if (!((BaseContainerFragment) getCurrentFragment() instanceof TutorFragment))
                            replaceFragment(TutorFragment_.builder().build());

                        return true;

                    case R.id.mTeacher:

                        if (!((BaseContainerFragment) getCurrentFragment() instanceof TeacherFragment))
                            replaceFragment(TeacherFragment_.builder().build());
                        return true;

                    case R.id.mSetting:
                        if (!((BaseContainerFragment) getCurrentFragment() instanceof SettingFragment))
                            replaceFragment(SettingFragment_.builder().build());
                        return true;

                    default:
                        return false;

                }
            }
        });
    }

//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                Log.i("isMyServiceRunning?", true + "");
//                return true;
//            }
//        }
//        Log.i("isMyServiceRunning?", false + "");
//        return false;
//    }
}


