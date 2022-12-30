package io.github.tenantmgt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import io.github.tenantmgt.Models.Bills;
import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityAddBillBinding;
import io.github.tenantmgt.databinding.ActivityGenerateInvoiceBinding;

public class AddBill extends AppCompatActivity {


    ActivityAddBillBinding binding;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bill);

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.backButton.setOnClickListener(v -> {
            finish();
        });

        binding.addBill.setOnClickListener(v -> {
            String billname = binding.billType.getText().toString();
            Double billamount = Double.parseDouble(binding.billAmount.getText().toString());
            Bills.getMap().put(billname, billamount);
            finish();
        });
    }
}