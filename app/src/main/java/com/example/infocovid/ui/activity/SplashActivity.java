package com.example.infocovid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.infocovid.R;
import com.example.infocovid.database.UserHelper;
import com.example.infocovid.model.Auth;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        UserHelper userHelper = UserHelper.getInstance(this);
        userHelper.open();
        Boolean exists = userHelper.checkIfExists();
        if (exists) {
            Auth.user = userHelper.all();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }, 3000L);
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                    finish();
                }
            }, 3000L);
        }
    }
}
