package io.github.tenantmgt_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import io.github.tenantmgt_android.R;
import io.github.tenantmgt_android.Services.AuthService;
import io.github.tenantmgt_android.Services.AuthStateService;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // check for auth

        AuthStateService auth = AuthService.getAuth();

        if(auth == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}