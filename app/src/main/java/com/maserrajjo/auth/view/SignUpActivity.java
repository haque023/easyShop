package com.maserrajjo.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.maserrajjo.R;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.buttonSignUp).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:
                Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(login);

            case R.id.buttonSignUp:
                Intent auth = new Intent(SignUpActivity.this, SignUpAuth.class);
                startActivity(auth);
        }
    }
}