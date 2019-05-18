package com.team9.bantuaku;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team9.bantuaku.Model.User;

public class Register extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etNama;
    private TextInputEditText etPassword;
    private Button btnDaftar;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etNama = findViewById(R.id.etNama);
        etPassword = findViewById(R.id.etPassword);
        btnDaftar = findViewById(R.id.btnDaftar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String nama = etNama.getText().toString();
                String password = etPassword.getText().toString();

                User penggunabaru = new User(nama, email, password);
                daftarPenggunaBaru(penggunabaru);
            }
        });

    }

    private void daftarPenggunaBaru(User penggunabaru){
        String key = myRef.push().getKey();
        myRef.child(key).setValue(penggunabaru);
    }
}
