package com.team9.bantuaku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class DetailBantuan extends AppCompatActivity {
    public static final String EXTRA_ID_USER = "com.team9.bantuaku.EXTRA_ID_USER";
    public static final String EXTRA_NAME = "com.team9.bantuaku.EXTRA_NAME";
    public static final String EXTRA_DATE_POST  = "com.team9.bantuaku.EXTRA_DATE_POST";
    public static final String EXTRA_DESC = "com.team9.bantuaku.EXTRA_DESC";
    public static final String EXTRA_TITLE = "com.team9.bantuaku.EXTRA_TITLE";
    public static final String EXTRA_SKILL  = "com.team9.bantuaku.EXTRA_SKILL";
    public static final String EXTRA_FEE = "com.team9.bantuaku.EXTRA_FEE";
    public static final String EXTRA_DATE = "com.team9.bantuaku.EXTRA_DATE";

    private TextView tvName;
    private TextView tvDate;
    private TextView tvJudul;
    private TextView tvDeskripsi;
    private TextView tvDeadline;
    private TextView tvBayaran;
    private TextView btnBantu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bantuan);

        //Toolbar init
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvName = findViewById(R.id.tv_name);
        tvDate = findViewById(R.id.tv_tanggal);
        tvJudul = findViewById(R.id.tv_judul);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvDeadline= findViewById(R.id.tv_tanggalDeadline);
        tvBayaran = findViewById(R.id.tv_bayaran);
        btnBantu = findViewById(R.id.btn_bantu);
        //Firebase User
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = getIntent();

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



        btnBantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailBantuan.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
