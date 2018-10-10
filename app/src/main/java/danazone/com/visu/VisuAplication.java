package danazone.com.visu;

import android.annotation.SuppressLint;
import android.app.Application;

import java.net.URISyntaxException;

import danazone.com.visu.common.Common;
import io.socket.client.IO;
import io.socket.client.Socket;

@SuppressLint("Registered")
public class VisuAplication extends Application {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(Common.URL_SOCKET);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
