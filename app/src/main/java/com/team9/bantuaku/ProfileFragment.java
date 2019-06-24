package com.team9.bantuaku;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.team9.bantuaku.Model.User;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ProfileFragment extends Fragment {
    private TextView namaUser,pointUser,jumlahTaskUser,tentangUser;
    private Button btnLogout;
    private Button editProfile;
    private ImageView profileImage;
    private FirebaseUser User;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private String imageUri;
    private String nama , tentang, profile_photo,point,selesai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //Toolbar init
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar_profile);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        //Get UI Component from layout
        namaUser = (TextView)view.findViewById(R.id.tv_namaUser);
        pointUser = (TextView)view.findViewById(R.id.tv_poin_profil);
        jumlahTaskUser = (TextView)view.findViewById(R.id.tv_selesai_profil);
        tentangUser = (TextView)view.findViewById(R.id.tv_tentang);
        btnLogout = (Button)view.findViewById(R.id.btnLogout);
        editProfile = (Button)view.findViewById(R.id.edit_profile);
        profileImage = (ImageView)view.findViewById(R.id.profile_image);

        //Firebase Initialization
        mStorageRef = FirebaseStorage.getInstance().getReference();
        User = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User").child(User.getUid());
        //Get Data From Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama = (String) dataSnapshot.child("nama").getValue();
                tentang = (String) dataSnapshot.child("biografi").getValue();
                point = (String)dataSnapshot.child("point").getValue().toString();
                selesai = (String) dataSnapshot.child("selesai").getValue().toString();
                profile_photo = (String)dataSnapshot.child("foto").getValue().toString();

                namaUser.setText(nama);
                pointUser.setText(point);
                jumlahTaskUser.setText(selesai);
                tentangUser.setText(tentang);

                Log.e(TAG, "GAK MASUKKKKK");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read app title value.", databaseError.toException());
            }
        });
        profile_photo = "nopp";
        Glide.with(view.getContext()).load(getProfileImageUri(profile_photo)).fitCenter().into(profileImage);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public String getProfileImageUri(String imageName){
        String namaFoto;
        if(imageName.equals("nopp")){
            namaFoto = "default.png";
        }else{
            namaFoto = imageName;
        }
        mStorageRef.child(namaFoto).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                imageUri = uri.toString();
                Log.i(TAG,"URI: "+uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG,"Error downloading the image. Cause:"+e);
            }
        });
        return imageUri;
    }
}
