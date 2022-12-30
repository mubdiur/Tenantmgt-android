package io.github.tenantmgt.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.billsList);
        LayoutInflater inflater = LayoutInflater.from(this);

        binding.refreshButton.setOnClickListener(v -> {
            db.collection("addbills").document(mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isComplete())  {
                        DocumentSnapshot document = task.getResult();
                        for (String key:
                             document.getData().keySet()) {
                            View view  = inflater.inflate(R.layout.list_items, linearLayout, false);
                            TextView title  = view.findViewById(R.id.titleTextView);
                            TextView description  = view.findViewById(R.id.descriptionText);

                            title.setText(key);
                            description.setText(document.getString(key));

                            linearLayout.addView(view);
                        }
                    }
                }
            });


        });
    }
}