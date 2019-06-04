package com.team9.bantuaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView poto_profile;
    private TextView point_profile;
    private TextView selesai_profile;
    private TextView biografi;
    private TextView keahlian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        poto_profile = findViewById(R.id.poto_profile);
        point_profile = findViewById(R.id.poin_profile);
        selesai_profile = findViewById(R.id.selesai_profile);
        biografi = findViewById(R.id.biografi);
        keahlian = findViewById(R.id.keahlian);
        initdummy();

    }

    public void initdummy(){
        point_profile.setText("999");
        selesai_profile.setText("999");
        biografi.setText("ini hanyalah init dummy jadi gak ada apa apa yo wess ini percobaan kecil sadja ");
        keahlian.setText("ini hanyalah init dummy jadi gak ada apa apa yo wess ini percobaan kecil sadja ");
    }

}
