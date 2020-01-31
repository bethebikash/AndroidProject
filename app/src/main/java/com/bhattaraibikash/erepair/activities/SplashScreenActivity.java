package com.bhattaraibikash.erepair.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.bhattaraibikash.erepair.R;
import com.bhattaraibikash.erepair.bll.LoginBLL;
import com.bhattaraibikash.erepair.strictmode.StrictModeClass;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");
                String password = sharedPreferences.getString("password", "");

                LoginBLL loginBLL = new LoginBLL();

                StrictModeClass.StrictMode();
                if (loginBLL.checkUser(username, password)) {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
