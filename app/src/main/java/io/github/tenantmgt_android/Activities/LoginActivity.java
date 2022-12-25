package io.github.tenantmgt_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.tenantmgt_android.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}