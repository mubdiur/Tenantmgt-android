package io.github.tenantmgt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.elevation.SurfaceColors;

import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityTenantHomeBinding;

public class TenantHome extends AppCompatActivity {
    ActivityTenantHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tenant_home);

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        Intent optionIntent = new Intent(this, OptionActivity.class);
        binding.optionsButton.setOnClickListener(v -> {
            startActivity(optionIntent);
        });
    }
}