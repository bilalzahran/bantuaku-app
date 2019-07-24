package com.team9.bantuaku;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.team9.bantuaku.Model.Bantuan;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {
    private FirebaseDatabase db;
    private DatabaseReference bantuanRef;
    private Query query;
    private CardView cardView;
    private RecyclerView bantuanList;
    private BantuanAdapter adapter;
    private List<Bantuan> listbantuan = new ArrayList<>();
    private final String TAG = "TaskListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        //Firebase init
        db = FirebaseDatabase.getInstance();
        bantuanRef = db.getReference("bantuan");
        query = bantuanRef.orderByChild("status").equalTo("Waiting Confirmation");

        //Component Init
        bantuanList = (RecyclerView)view.findViewById(R.id.taskList);

        bantuanList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bantuanList.setHasFixedSize(true);

        //Adapter
        getBantuan();
        adapter = new BantuanAdapter();
        bantuanList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bantuanList.setAdapter(adapter);

        adapter.setOnBantuanClickListener(new BantuanAdapter.OnBantuanListener() {
            @Override
            public void onItemClick(Bantuan bantuan) {
                Intent intent = new Intent(getContext(),DetailBantuan.class);
                intent.putExtra(DetailBantuan.EXTRA_NAME, bantuan.getFirstNameUser());
                intent.putExtra(DetailBantuan.EXTRA_DATE_POST, bantuan.getTanggalPost());
                intent.putExtra(DetailBantuan.EXTRA_TITLE, bantuan.getJudul());
                intent.putExtra(DetailBantuan.EXTRA_DESC, bantuan.getDeskripsi());
                intent.putExtra(DetailBantuan.EXTRA_SKILL, bantuan.getKeahlian());
                intent.putExtra(DetailBantuan.EXTRA_DATE, bantuan.getDeadline());
                intent.putExtra(DetailBantuan.EXTRA_FEE, bantuan.getBayaran());
                intent.putExtra(DetailBantuan.EXTRA_ID_USER,bantuan.getIdUser());
                startActivity(intent);
            }
        });

        return view;
    }

    public void getBantuan(){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listbantuan.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Bantuan bantuan = ds.getValue(Bantuan.class);
                    listbantuan.add(bantuan);
                }
                adapter.setData(listbantuan);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG,"Error when get data to recycle view" + databaseError);
            }
        });
    }

    public String joinList(List<String> bantuan){
        return TextUtils.join(", ",bantuan);
    }

}
