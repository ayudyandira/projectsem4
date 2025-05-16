package com.example.modullayout;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView forgotPassword, registerText, termsText;
    private ImageView logo, sosmedIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);

        // Inisialisasi elemen UI
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login);
        forgotPassword = findViewById(R.id.forgot);
        registerText = findViewById(R.id.register);
        termsText = findViewById(R.id.terms);
        logo = findViewById(R.id.logo);
        sosmedIcon = findViewById(R.id.sosmed);

        // Menjadikan "Register Now" dapat diklik
        registerText.setText(Html.fromHtml("Don't have an account? <u>Register Now</u>"));
        registerText.setMovementMethod(LinkMovementMethod.getInstance());

        // Menjadikan "Terms & Conditions" dapat diklik
        termsText.setText(Html.fromHtml("By signing up, you are agree with our <u>Terms & Conditions</u>"));
        termsText.setMovementMethod(LinkMovementMethod.getInstance());

        // Event klik pada tombol Login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Event klik pada "Forgot Password?"
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // Event klik pada "Register Now"
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Register Now Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // Event klik pada "Terms & Conditions"
        termsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Terms & Conditions Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
