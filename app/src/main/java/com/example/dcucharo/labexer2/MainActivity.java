package com.example.dcucharo.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnSaveSharedPref, btnLoadIntStorage, btnNext;
    TextView tvDisplay;
    SharedPreferences preferences;
    FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnSaveSharedPref = (Button) findViewById(R.id.btn_save_shared_pref);
        btnLoadIntStorage =  (Button) findViewById(R.id.btn_save_int_storage);
        btnNext = (Button) findViewById(R.id.btn_next);

        preferences = getSharedPreferences("preferences.xml",Context.MODE_PRIVATE);
    }
    public void savePreference(View view) {
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("user",etUsername.getText().toString());
        editor.putString("pwd",etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this,"Saved in Preferences",Toast.LENGTH_SHORT).show();
    }
    public void saveIntStorage(View view) {
        String message =  etUsername.getText().toString() + "  " + etPassword.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this,"Saved in Internal Storage.", Toast.LENGTH_SHORT).show();
    }
    public void callSecondActivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
