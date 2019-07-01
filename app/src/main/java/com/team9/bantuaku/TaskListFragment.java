package com.team9.bantuaku;

import android.nfc.Tag;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team9.bantuaku.Model.Bantuan;

import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {
    private FirebaseDatabase db;
    private DatabaseReference bantuanRef;
    private RecyclerView bantuanList;
    private BantuanAdapter adapter;
    private List<Bantuan> listbantuan = new ArrayList<>();
    private final String TAG = "TaskListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        //Firebase init
        db = FirebaseDatabase.getInstance();
        bantuanRef = db.getReference("bantuan");

        //Component Init
        bantuanList = (RecyclerView)view.findViewById(R.id.tasklist);

        //Adapter
        getBantuan();
        adapter = new BantuanAdapter(view.getContext());
        bantuanList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bantuanList.setAdapter(adapter);

        return view;
    }

    public void getBantuan(){
        System.out.println("Masuuuuukkk!!!!!");
        bantuanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listbantuan.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Bantuan bantuan = ds.getValue(Bantuan.class);
                    listbantuan.add(bantuan);
                }
                adapter.setData(listbantuan);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i(TAG,"Error when get data to recycle view" + databaseError);
            }
        });
    }
}
