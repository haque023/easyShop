package com.maserrajjo.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.maserrajjo.R;

public class SignUpAuth extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_auth);
        findViewById(R.id.buttonConfirm).setOnClickListener(this);
        findViewById(R.id.resend).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonConfirm:
                Intent login = new Intent(SignUpAuth.this, LoginActivity.class);
                startActivity(login);

            case R.id.resend:
//                Intent auth = new Intent(SignUpAuth.this, SignUpAuth.class);
//                startActivity(auth);
        }

    }
}