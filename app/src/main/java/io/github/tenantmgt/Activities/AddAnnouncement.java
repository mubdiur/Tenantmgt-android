package io.github.tenantmgt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityAddAnnouncementBinding;
import io.github.tenantmgt.databinding.ActivityAddBillBinding;

public class AddAnnouncement extends AppCompatActivity {

    ActivityAddAnnouncementBinding binding;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_announcement);

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        binding.backButton.setOnClickListener(v -> {
            finish();
        });

        binding.addAnnouncement.setOnClickListener(v -> {
            String announcement = binding.announcementText.getText().toString();
            Map<String, String> map = new HashMap<>();
            map.put("message", announcement);
            db.collection("announcements").document(mAuth.getCurrentUser().getEmail()).set(map).addOnCompleteListener(e -> {
                finish();
            });
        });
    }
}