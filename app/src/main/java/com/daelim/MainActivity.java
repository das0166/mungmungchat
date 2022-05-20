package com.daelim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    WebSocketClient ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
        ws = new WebSocketClient(new URI("ws://61.83.168.88:4877")) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.e("!!!","onOpen");
                ws.send("배다슬");
            }

            @Override
            public void onMessage(String s) {
                Log.e("!!!","onMessage s : "+s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.e("!!!","onClose : "+s);
            }

            @Override
            public void onError(Exception e) {
                Log.e("!!!","onError");
                e.printStackTrace();
            }
        };
        ws.connect();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void onResume() {
        super.onResume();
    }
}