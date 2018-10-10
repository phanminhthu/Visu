package danazone.com.visu.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import danazone.com.visu.BaseApp;
import danazone.com.visu.common.Common;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@SuppressLint("Registered")
public class VisuService extends Service {
    private Context context;
    private Socket mSocket;

    public VisuService(Context applicationContext) {
        super();
        context = applicationContext;
        Log.i("HERE", "here service created!");
    }

    public VisuService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Intent broadcastIntent = new Intent("ac.in.ActivityRecognition.RestartSensor");
//        broadcastIntent.putExtra("", "");
//        sendBroadcast(broadcastIntent);

//        try {
//            mSocket = IO.socket(Common.URL_SOCKET);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        BaseApp app = (BaseApp) getApplication().getApplicationContext();
        mSocket = app.getSocket();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mSocket.connect();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
