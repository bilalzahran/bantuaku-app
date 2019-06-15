package com.team9.bantuaku;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class EditFotoProfilDialog extends DialogFragment {
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "EditProfile";
    ImageView preview;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        preview = (ImageView) getActivity().findViewById(R.id.poto_profile);
        builder.setTitle(R.string.edit_foto_profil)
                .setItems(R.array.edit_foto, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Log.i(TAG,"Hapus Foto Has Clicked");

                        }else if(which==1){
                            Log.i(TAG,"Upload Foto Dari Galeri Has Clicked");
                            choosePhotoFromGalery();
                        }else{
                            Log.i(TAG,"Ambil Foto Melalui Kamera Has Clicked");
                        }
                    }
                });
        return builder.create();
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.i(TAG,"It's get called");
//        if(resultCode == SELECT_PICTURE){
//            Uri selectedImageUri = data.getData();
//            if(selectedImageUri != null){
//                String path = getPathFromURI(selectedImageUri);
//                preview.setImageURI(selectedImageUri);
//            }
//        }
//
//    }
    public void choosePhotoFromGalery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getActivity().startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public String getPathFromURI(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null,null,null);
        if(cursor.moveToFirst()){
            int coloumn_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(coloumn_index);
        }
        cursor.close();
        return res;
    }
}
