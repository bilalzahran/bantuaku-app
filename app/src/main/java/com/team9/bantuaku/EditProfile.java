package com.team9.bantuaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.team9.bantuaku.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EditProfile extends AppCompatActivity {
    private ImageButton btnBack,btnDone;
    private EditText etTentang,etTelepon,etNama;
    private TextView linkEditFotoProfil;
    private ImageView preview;
    private FirebaseUser User;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    public User userData;
    private static final int SELECT_PICTURE = -1;
    Uri selectedImageUri;
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
        linkEditFotoProfil = (TextView) findViewById(R.id.btnEditFoto);

        //Firabase Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();

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
                User = FirebaseAuth.getInstance().getCurrentUser();
                String nama = etNama.getText().toString();
                String tentang = etTentang.getText().toString();
                String no_telp = etTelepon.getText().toString();
                String profile_photo = User.getUid()+".png";
                userData.setNama(nama);
                userData.setBiografi(tentang);
                userData.setNo_telp(no_telp);
                userData.setFoto(profile_photo);
                reference.setValue(userData);
                uploadImage(selectedImageUri);
                finish();
            }
        });
        linkEditFotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG,"It's get called "+data.getData());
        preview = findViewById(R.id.poto_profile);
        if(resultCode == SELECT_PICTURE){
           selectedImageUri = data.getData();
            if(selectedImageUri != null){
                preview.setImageURI(selectedImageUri);
            }
        }
    }
    public void uploadImage(Uri ImagePath){
        if(ImagePath != null){
            User = FirebaseAuth.getInstance().getCurrentUser();
            StorageReference childRef = mStorageRef.child(User.getUid()+".png");
            //Uploading the Image
            UploadTask uploadTask = childRef.putFile(ImagePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Log.i(TAG,"Successfull uploading");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i(TAG,"Failed to upload photo. Cause: "+e);
                }
            });
        }
    }
    public void openDialog(){
        EditFotoProfilDialog dialog = new EditFotoProfilDialog();
        dialog.show(getSupportFragmentManager(), "Edit Foto Profil");
    }
}
