package com.example.modullayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityOne extends AppCompatActivity {

    private ImageView imageHead, imageHair, imageMoustache, imageEyes, imageEyebrows, imageBeard;
    private CheckBox checkHead, checkHair, checkMoustache, checkEyes, checkEyebrow, checkBeard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mrhead);

        TextView email;
        Button btnCall;

        email = findViewById(R.id.titleText);
        Intent intent = getIntent();
        email.setText("Welcome, " + intent.getStringExtra("email_key"));

        TextView password = findViewById(R.id.passwordText);
        password.setText("Password: " + intent.getStringExtra("password_key"));

        // Tombol btnCall untuk navigasi ke com.example.modullayout.ContactUsActivity
        btnCall = findViewById(R.id.btn_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent eksplisit untuk navigasi ke com.example.modullayout.ContactUsActivity
                Intent contactIntent = new Intent(ActivityOne.this, ContactUsActivity.class);
                startActivity(contactIntent);
            }
        });


    // Inisialisasi Komponen UI
        imageHead = findViewById(R.id.imageHead);
        imageHair = findViewById(R.id.imageHair);
        imageMoustache = findViewById(R.id.imageMoustache);
        imageEyes = findViewById(R.id.imageEyes);
        imageEyebrows = findViewById(R.id.imageEyebrows);
        imageBeard = findViewById(R.id.imageBeard);

        checkHead = findViewById(R.id.checkHead);
        checkHair = findViewById(R.id.checkHair);
        checkMoustache = findViewById(R.id.checkMoustache);
        checkEyes = findViewById(R.id.checkEyes);
        checkEyebrow = findViewById(R.id.checkEyebrow);
        checkBeard = findViewById(R.id.checkBeard);

        // Event Listener untuk Checkbox
        checkHead.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageHead.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));

        checkHair.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageHair.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));

        checkMoustache.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageMoustache.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));

        checkEyes.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageEyes.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));

        checkEyebrow.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageEyebrows.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));

        checkBeard.setOnCheckedChangeListener((buttonView, isChecked) ->
                imageBeard.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE));
    }
}
