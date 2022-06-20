package com.daelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText id = findViewById(R.id.id);
        EditText pw = findViewById(R.id.pw);
        CheckBox autologin = findViewById(R.id.autologin);
        Button login = findViewById(R.id.login);
        SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        login.setOnClickListener(new View.OnClickListener() {
            String sa_id, sa_pw;
            Boolean auto;
            @Override
            public void onClick(View view) {
                editor.putString("id", id.getText().toString());
                editor.putString("pw", pw.getText().toString());
                editor.putBoolean("autologin",autologin.isChecked());
                editor.commit();
                sa_id=sp.getString("id","");
                sa_pw=sp.getString("pw","");
                auto=sp.getBoolean("autologin",true);
                Intent intent = new Intent(LoginActivity.this,ChatActivity.class);
                intent.putExtra("id",sa_id);
                intent.putExtra("pw",sa_pw);
                intent.putExtra("auto",auto);
                startActivity(intent);
            }
        });
        if (sp.getBoolean("autologin", true)) {
            id.setText(sp.getString("id", ""));
            pw.setText(sp.getString("pw", ""));
            autologin.setChecked(true);
        } else {
                editor.clear();
                editor.commit();
        }
        }
    }
