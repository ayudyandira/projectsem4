package com.example.modullayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contactus);

        Button buttonCall = findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit intent untuk panggilan telepon
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+123456789"));
                startActivity(callIntent);
            }
        });

        Button buttonMessage = findViewById(R.id.buttonMessage);
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit intent untuk mengirim pesan teks
                Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:+123456789"));
                messageIntent.putExtra("sms_body", "Hello, Nama Saya Ayudya Nandira Afifah ...");
                startActivity(messageIntent);
            }
        });

        Button buttonWhatsApp = findViewById(R.id.buttonWhatsApp);
        buttonWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit intent untuk WhatsApp
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/123456789"));
                startActivity(whatsappIntent);
            }
        });

        Button buttonOpenWeb = findViewById(R.id.buttonOpenWeb);
        buttonOpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implicit intent untuk membuka website
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"));
                startActivity(webIntent);
            }
        });
    }
}

