package com.team9.bantuaku;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team9.bantuaku.Model.User;

public class Register extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etNama;
    private TextInputEditText etPassword;
    private TextInputEditText etRepassword;
    private TextInputEditText etTelepon;
    private Button btnDaftar;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etNama = findViewById(R.id.etNama);
        etPassword = findViewById(R.id.etPassword);
        etRepassword = findViewById(R.id.etRePassword);
        etTelepon = findViewById(R.id.etPhone);
        btnDaftar = findViewById(R.id.btnDaftar);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("User");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String nama = etNama.getText().toString();
                String password = etPassword.getText().toString();
                String repassword = etRepassword.getText().toString();
                String telepon = etTelepon.getText().toString();

                // Make Validation
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Email tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(nama)){
                    Toast.makeText(getApplicationContext(), "Nama tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(telepon)){
                    Toast.makeText(getApplicationContext(), "No telepon tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                if(!TextUtils.equals(password,repassword)){
                    Toast.makeText(getApplicationContext(), "Password dan Konfirmasi Password Tidak Sama",Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String telepon = etTelepon.getText().toString();
                            String nama = etNama.getText().toString();
                            FirebaseUser user = mAuth.getCurrentUser();
                            User penggunabaru = new User(nama ,telepon);
                            daftarPenggunaBaru(penggunabaru);
                            Intent intent = new Intent(Register.this, RegisterConfirmation.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });


    }

    private void daftarPenggunaBaru(User penggunabaru){

        String key = myRef.push().getKey();
        myRef.child(key).setValue(penggunabaru);
    }
}
