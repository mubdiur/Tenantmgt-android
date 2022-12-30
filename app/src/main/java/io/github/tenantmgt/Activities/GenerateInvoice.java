package io.github.tenantmgt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import io.github.tenantmgt.Models.Bills;
import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityGenerateInvoiceBinding;
import io.github.tenantmgt.databinding.ActivityOptionBinding;

public class GenerateInvoice extends AppCompatActivity {

    ActivityGenerateInvoiceBinding binding;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_generate_invoice);

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.backButton.setOnClickListener(v -> {
            finish();
        });

        Intent addBillIntent = new Intent(this, AddBill.class);
        Map<String, Double> bills = Bills.getMap();

        binding.addBill.setOnClickListener(v -> {
            startActivity(addBillIntent);
        });

        binding.generateInvoice.setOnClickListener(v -> {
            
        });
    }
}