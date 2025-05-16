package com.example.modullayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView forgotPassword, registerText, termsText;
    private ImageView logo, sosmedIcon;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);

        // Firebase init
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // UI elements
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login);
        forgotPassword = findViewById(R.id.forgot);
        registerText = findViewById(R.id.register);
        termsText = findViewById(R.id.terms);
        logo = findViewById(R.id.logo);
        sosmedIcon = findViewById(R.id.sosmed);

        // Login button click
        // Login button click
        loginButton.setOnClickListener(v -> {
            if (!validateForm()) return;

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            loginUser(email, password);
        });


        // Register TextView
        registerText.setText(Html.fromHtml("Don't have an account? <u>Register Now</u>"));
        registerText.setMovementMethod(LinkMovementMethod.getInstance());
        registerText.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            signupUser(email, password);
            Toast.makeText(MainActivity.this, "Register Now Clicked!", Toast.LENGTH_SHORT).show();
        });



        // Terms TextView
        termsText.setText(Html.fromHtml("By signing up, you agree with our <u>Terms & Conditions</u>"));
        termsText.setMovementMethod(LinkMovementMethod.getInstance());

        // Forgot password
        forgotPassword.setOnClickListener(v -> Toast.makeText(this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show());


        // Terms click
        termsText.setOnClickListener(v -> Toast.makeText(this, "Terms & Conditions Clicked!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
            intent.putExtra("email_key", user.getEmail()); // kalau perlu email
            startActivity(intent);
            finish(); // mencegah kembali ke login
        } else {
            Toast.makeText(MainActivity.this, "Log In First", Toast.LENGTH_SHORT).show();
        }
    }




    private void loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("LOGIN", "signInWithEmail:success");
                        Toast.makeText(MainActivity.this, "Login berhasil: " + user.getEmail(), Toast.LENGTH_SHORT).show();

                        updateUI(user);
                    } else {
                        Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Login gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signupUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("SIGNUP", "createUserWithEmail:success");
                        Toast.makeText(MainActivity.this, "Registrasi berhasil: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        updateUI(user);
                    } else {
                        Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Registrasi gagal: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private boolean validateForm() {
        boolean result = true;

        if (emailInput.getText().toString().trim().isEmpty()) {
            emailInput.setError("Email tidak boleh kosong");
            result = false;
        } else {
            emailInput.setError(null);
        }

        if (passwordInput.getText().toString().trim().isEmpty()) {
            passwordInput.setError("Password tidak boleh kosong");
            result = false;
        } else {
            passwordInput.setError(null);
        }

        return result;
    }



}
