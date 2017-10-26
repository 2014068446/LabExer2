package com.example.dcucharo.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    Button btnLoadPreference, btnLoadIntStorage, btnClear, btnBack;
    TextView tvDisplay;
    FileOutputStream fos;
    FileInputStream fis;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        btnLoadPreference = findViewById(R.id.btn_load_shared_pref);
        btnLoadIntStorage = findViewById(R.id.btn_load_int_storage);
        btnClear = findViewById(R.id.btn_clear);
        btnBack = findViewById(R.id.btn_back);
        tvDisplay = findViewById(R.id.tv_display_pref);
        preferences = getSharedPreferences("preferences.xml",Context.MODE_PRIVATE);
    }
    public void callMainActivity(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void clearSaved(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            String clear = "";
            fos.write(clear.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void loadIntStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }
    public void loadPreferences(View view) {
        String user = preferences.getString("user","");
        String password = preferences.getString("pwd","");
        tvDisplay.setText(user + " " + password);
    }

}
