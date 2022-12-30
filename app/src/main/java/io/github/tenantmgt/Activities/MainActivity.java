package io.github.tenantmgt.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import io.github.tenantmgt.R;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));
    }

    @Override
    protected void onStart() {

        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        Intent tenantIntent = new Intent(this, TenantHome.class);
        Intent adminIntent = new Intent(this, AdminHome.class);
        Intent loginIntent = new Intent(this, LoginActivity.class);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Log.d("TAG", "onStart: User logged in");
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Query roles = db.collection("roles").whereEqualTo("email", currentUser.getEmail());

            roles.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                    String role = document.getData().get("role").toString();
                                    switch (role) {
                                        case "ROLE_ADMIN":
                                            startActivity(adminIntent);
                                            finish();
                                            break;
                                        case "ROLE_TENANT":
                                            startActivity(tenantIntent);
                                            finish();
                                            break;
                                        default:
                                            mAuth.signOut();
                                            startActivity(loginIntent);
                                    }
                                }
                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else {
            startActivity(loginIntent);
            finish();
        }
    }
}