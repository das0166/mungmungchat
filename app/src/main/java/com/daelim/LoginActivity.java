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
    Boolean loginChecked;

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
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });

        if (sp.getBoolean("autologin", false)) {
            id.setText(sp.getString("id", ""));
            pw.setText(sp.getString("pw", ""));
            autologin.setChecked(true);
//        } else {
//                if (loginChecked) {
//                    editor.putString("id", id.getText().toString());
//                    editor.putString("pw", pw.getText().toString());
//                    editor.putBoolean("autologin", true);
//                    editor.commit();
//                }
//            }

            autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        loginChecked = true;
                    } else {
                        loginChecked = false;
                        editor.clear();
                        editor.commit();
                    }
                }
            });


        }

    }
}