package io.github.tenantmgt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;

import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityOptionBinding;

public class OptionActivity extends AppCompatActivity {
    ActivityOptionBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_option);

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        mAuth = FirebaseAuth.getInstance();

        binding.emailView.setText(mAuth.getCurrentUser().getEmail());

        Intent loginActivity = new Intent(this, LoginActivity.class);
        binding.logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(loginActivity);
            finish();
        });

        binding.backButton.setOnClickListener(v -> {
            finish();
        });
    }
}