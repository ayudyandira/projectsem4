package com.example.modullayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference contactRef;
    private List<Contact> contactList = new ArrayList<>();
    private ContactAdapter adapter;

    private RecyclerView recyclerView;
    private EditText inputName, inputPhone;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        contactRef = FirebaseDatabase.getInstance().getReference("contacts").child(user.getUid());

        inputName = findViewById(R.id.inputName);
        inputPhone = findViewById(R.id.inputPhone);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ContactAdapter(contactList, new ContactAdapter.OnItemClickListener() {
            @Override
            public void onEdit(Contact contact) {
                inputName.setText(contact.getName());
                inputPhone.setText(contact.getPhone());
                btnAdd.setText("Update");
                btnAdd.setOnClickListener(v -> updateContact(contact));
            }

            @Override
            public void onDelete(Contact contact) {
                contactRef.child(contact.getId()).removeValue();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> addContact());

        loadContacts();
    }

    private void addContact() {
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();

        if (name.isEmpty() || phone.isEmpty()) return;

        String id = contactRef.push().getKey();
        Contact contact = new Contact(id, name, phone);

        contactRef.child(id).setValue(contact);
        inputName.setText("");
        inputPhone.setText("");
    }

    private void updateContact(Contact contact) {
        contact.setName(inputName.getText().toString());
        contact.setPhone(inputPhone.getText().toString());
        contactRef.child(contact.getId()).setValue(contact);

        btnAdd.setText("Add");
        btnAdd.setOnClickListener(v -> addContact());

        inputName.setText("");
        inputPhone.setText("");
    }

    private void loadContacts() {
        contactRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Contact contact = data.getValue(Contact.class);
                    contactList.add(contact);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContactListActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

