package com.team9.bantuaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import com.team9.bantuaku.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EditProfile extends AppCompatActivity {
    private ImageButton btnBack,btnDone;
    private EditText etTentang,etTelepon,etNama;
    private FirebaseUser User;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    public User userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //Init Custom Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Init Form Component
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnDone = (ImageButton) findViewById(R.id.btnDone);
        etNama = (EditText) findViewById(R.id.etNama);
        etTentang = (EditText) findViewById(R.id.etTentang);
        etTelepon = (EditText) findViewById(R.id.etTelepon);

        //Firebase - Get User Data
        User = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User").child(User.getUid());
        //Set Data to Form

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(User.class);
                etNama.setText(userData.getNama());
                etTentang.setText(userData.getBiografi());
                etTelepon.setText(userData.getNo_telp());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to load user data.", databaseError.toException());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String tentang = etTentang.getText().toString();
                String no_telp = etTelepon.getText().toString();
                userData.setNama(nama);
                userData.setBiografi(tentang);
                userData.setNo_telp(no_telp);
                reference.setValue(userData);
                finish();
            }
        });
    }
}
