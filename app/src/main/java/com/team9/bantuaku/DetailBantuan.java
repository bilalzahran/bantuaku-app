package com.team9.bantuaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.team9.bantuaku.Helper.ImageHelper;
import com.team9.bantuaku.Helper.KeahlianHelper;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailBantuan extends AppCompatActivity {
    public static final String EXTRA_ID_USER = "com.team9.bantuaku.EXTRA_ID_USER";
    public static final String EXTRA_NAME = "com.team9.bantuaku.EXTRA_NAME";
    public static final String EXTRA_DATE_POST  = "com.team9.bantuaku.EXTRA_DATE_POST";
    public static final String EXTRA_DESC = "com.team9.bantuaku.EXTRA_DESC";
    public static final String EXTRA_TITLE = "com.team9.bantuaku.EXTRA_TITLE";
    public static final String EXTRA_SKILL  = "com.team9.bantuaku.EXTRA_SKILL";
    public static final String EXTRA_FEE = "com.team9.bantuaku.EXTRA_FEE";
    public static final String EXTRA_DATE = "com.team9.bantuaku.EXTRA_DATE";
    public static final String firebaseStorageUrl = "gs://bantuaku-team9.appspot.com/";

    private FirebaseStorage mStorage;

    private String keahlian;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bantuan);

        //Toolbar init
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvDate = findViewById(R.id.tv_tanggal);
        TextView tvJudul = findViewById(R.id.tv_judul);
        TextView tvDeskripsi = findViewById(R.id.tv_deskripsi);
        TextView tvDeadline= findViewById(R.id.tv_tanggalDeadline);
        TextView tvBayaran = findViewById(R.id.tv_bayaran);
        TextView btnBantu = findViewById(R.id.btn_bantu);
        FlexboxLayout layout= findViewById(R.id.keahlian);
        CircleImageView ivProfile = findViewById(R.id.iv_photo);

        //Firebase User
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Firebase Storage
        mStorage = FirebaseStorage.getInstance();
        Intent intent = getIntent();

        //Helper Initialization
        ImageHelper imageHelper = new ImageHelper();
        KeahlianHelper keahlianHelper = new KeahlianHelper();

        if(user.getUid().equals(intent.getStringExtra(EXTRA_ID_USER))){
            tvName.setText("You");
            btnBantu.setVisibility(View.GONE);
        }else{
            tvName.setText(intent.getStringExtra(EXTRA_NAME));
            btnBantu.setVisibility(View.VISIBLE);
        }

        tvDate.setText(intent.getStringExtra(EXTRA_DATE_POST));
        tvJudul.setText(intent.getStringExtra(EXTRA_TITLE));
        tvDeskripsi.setText(intent.getStringExtra(EXTRA_DESC));
        tvDeadline.setText(intent.getStringExtra(EXTRA_DATE));
        tvBayaran.setText(String.valueOf(intent.getIntExtra(EXTRA_FEE,1)));
        keahlian = intent.getStringExtra(EXTRA_SKILL);
        idUser = intent.getStringExtra(EXTRA_ID_USER);
        keahlianHelper.setKeahlian(this,keahlian, layout);
        imageHelper.setProfileImage(mStorage,idUser,ivProfile);

        btnBantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailBantuan.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
