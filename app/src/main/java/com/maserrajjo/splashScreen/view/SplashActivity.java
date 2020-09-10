package com.maserrajjo.splashScreen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.maserrajjo.R;
import com.maserrajjo.auth.view.LoginActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas);
        go();
    }

    private void go()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    System.out.println("i am from splash screen");
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

            }
        },2000);

    }
}