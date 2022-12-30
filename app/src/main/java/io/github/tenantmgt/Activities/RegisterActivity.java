package io.github.tenantmgt.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.tenantmgt.R;
import io.github.tenantmgt.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getWindow().setStatusBarColor(SurfaceColors.SURFACE_0.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_0.getColor(this));

        binding.goToLoginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

        binding.registerButton.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String password = binding.editPassword.getText().toString();
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            }
                        }
                    });
        });
    }
}