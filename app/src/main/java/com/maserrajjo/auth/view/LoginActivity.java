package com.maserrajjo.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.maserrajjo.R;
import com.maserrajjo.main.MainActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.createAccount).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main);
                break;

            case R.id.createAccount:
                Intent signUp = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signUp);
                break;

        }
    }

}