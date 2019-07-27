package com.team9.bantuaku.Helper;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.team9.bantuaku.DetailBantuan;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageHelper {
    public static final String firebaseStorageUrl = "gs://bantuaku-team9.appspot.com/";

    public void setProfileImage(FirebaseStorage mStorage, String imageName, final CircleImageView target){
        String image = imageName.concat(".png");
        StorageReference storageReference = mStorage.getReferenceFromUrl(firebaseStorageUrl).child(image);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(target);
            }
        });

    }
}
