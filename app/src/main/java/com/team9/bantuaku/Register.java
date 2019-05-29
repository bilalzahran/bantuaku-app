package com.team9.bantuaku;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team9.bantuaku.Model.User;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;


public class Register extends AppCompatActivity {
    private TextInputEditText etEmail;
    private TextInputEditText etNama;
    private TextInputEditText etPassword;
    private TextInputEditText etRepassword;
    private TextInputEditText etTelepon;
    private TextView linkMasuk;
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
        linkMasuk = findViewById(R.id.linkMasuk);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("User");
        //Validation
        final AwesomeValidation mAwesomeValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);

        if(etNama.getText().toString() == null){
            mAwesomeValidation.addValidation(this, R.id.tilNama, "/^\\d*\\.?\\d+$/",R.string.err_no_name);
        }else{
            mAwesomeValidation.addValidation(this,R.id.tilNama,"[a-zA-Z\\s]+",R.string.err_nama);
        }

        if(etEmail.getText().toString() == null){
            mAwesomeValidation.addValidation(this,R.id.tilEmail,"/^\\d*\\.?\\d+$/", R.string.err_no_email);
        }else{
            mAwesomeValidation.addValidation(this,R.id.tilEmail, Patterns.EMAIL_ADDRESS, R.string.err_email);
        }

        if(etTelepon.getText().toString() == null){
            mAwesomeValidation.addValidation(this, R.id.tilPhone,"/^\\d*\\.?\\d+$/",R.string.err_no_telp);
        }else {
            mAwesomeValidation.addValidation(this, R.id.tilPhone,"^[0-9]{12,13}$",R.string.err_telp);
        }

        if(etPassword.getText().toString() == null){
            mAwesomeValidation.addValidation(this,R.id.tilPassword,"/^\\d*\\.?\\d+$/",R.string.err_no_password);
        }else{
            mAwesomeValidation.addValidation(this, R.id.tilPassword,"(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$",R.string.err_password);
        }

        mAwesomeValidation.addValidation(this, R.id.tilRepassword, R.id.tilPassword, R.string.err_password_conf);


        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String nama = etNama.getText().toString();
                String password = etPassword.getText().toString();
                String repassword = etRepassword.getText().toString();
                String telepon = etTelepon.getText().toString();

              if(mAwesomeValidation.validate()){
                  mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              String telepon = etTelepon.getText().toString();
                              String nama = etNama.getText().toString();
                              FirebaseUser user = mAuth.getCurrentUser();
                              User penggunabaru = new User(nama ,telepon);
                              daftarPenggunaBaru(penggunabaru,user.getUid());
                              Intent intent = new Intent(Register.this, RegisterConfirmation.class);
                              startActivity(intent);
                              finish();
                          }
                      }
                  });
              }
            }
        });

        linkMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void daftarPenggunaBaru(User penggunabaru,String key){
        myRef.child(key).setValue(penggunabaru);
    }
}
