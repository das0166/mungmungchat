package com.daelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.net.URI;

public class ChatActivity extends AppCompatActivity {
    String id,pw;
    Boolean auto;
    WebSocketClient ws;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh:mm:ss");
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ImageButton out = findViewById(R.id.out);
        TextView getid = findViewById(R.id.id);
        EditText nae = findViewById(R.id.nae);
        ImageButton send = findViewById(R.id.send);

        id=getIntent().getStringExtra("id");
        pw=getIntent().getStringExtra("pw");
        auto=getIntent().getBooleanExtra("auto",true);


        try {
            ws = new WebSocketClient(new URI("ws://61.83.168.88:4877")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.e("!!!","onOpen");
                    ws.send("LOGIN|"+id+"|"+pw);
                    if(id != null){
                        getid.setText(id + "님 환영합니다.");
                    }
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ws.send("CHAT|"+id+"|"+getTime()+"|"+ nae.getText());
                            nae.setText(null);
                        }
                    });
                    out.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ws.close();
                            Intent intent = new Intent(ChatActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });
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