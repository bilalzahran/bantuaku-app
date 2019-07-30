package com.team9.bantuaku;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team9.bantuaku.Model.Bantuan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTaskFragment extends Fragment {
    EditText dateedittext,et_judul,et_deskripsi,et_fee;
    Calendar myCalendar;
    ChipGroup cp_keahlian;
    Button kirim;
    FirebaseDatabase database;
    FirebaseUser User;
    DatabaseReference mRef,mUser;
    List<String> keahlianlist = new ArrayList<>();
    List<String> idTalent = new ArrayList<>();
    DatePickerDialog.OnDateSetListener date;
    static String deskripsi,deadline,judul,tanggal,namaUser,TAG="AddTaskFragment",dateNow;
    static String keahlian;
    static Integer fee;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(""); //Remove App title from toolbar

        //Init Calendar for DatePicker
        myCalendar = Calendar.getInstance();

        //Component Init
        dateedittext = (EditText)view.findViewById(R.id.target_date);
        cp_keahlian = (ChipGroup)view.findViewById(R.id.chip_keahlian);
        kirim = (Button)view.findViewById(R.id.btn_kirim);
        et_judul = (EditText)view.findViewById(R.id.et_judul_pekerjaan);
        et_deskripsi = (EditText)view.findViewById(R.id.et_deskripsi_pekerjaan);
        et_fee = (EditText)view.findViewById(R.id.et_fee);

        //Firebase init\
        User = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("bantuan");
        mUser = database.getReference("User").child(User.getUid()); //This code will be remove by SharedPreferance
        //Set Chip in Chipgroup keahlian
        setChip(cp_keahlian,view);

        //Date Selecting
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(year,month,dayOfMonth);
                updateLabel();
            }
        };

        //Edit Text Date
        dateedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DATE))
                .show();
            }
        });
        //Button kirim action
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = et_judul.getText().toString();
                deskripsi = et_deskripsi.getText().toString();
                fee = Integer.valueOf(et_fee.getText().toString());
                deadline = dateedittext.getText().toString();
                idTalent.add("");
                keahlian = TextUtils.join(", ",keahlianlist);
                tanggal = dateedittext.getText().toString();
                namaUser = "Bilal Zahran";
                dateNow = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                DialogAddTask dialog = new DialogAddTask();
                dialog.show(getFragmentManager(),"dialogBottomSheetAddtask");
            }
        });
        //Chipgroup get selected chip
        for(int i=0;i < cp_keahlian.getChildCount();i++){
            Chip chip = cp_keahlian.findViewById(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    keahlianlist.add(buttonView.getText().toString());
                }else{
                    keahlianlist.remove(buttonView.getText().toString());
                }
                }
            });
        }
        return view;
    }

    // Helper Function & Procedure
    private void setChip(ChipGroup chipgroup,View view){
        String[] keahlian_array = getResources().getStringArray(R.array.keahlian);

        for(int i = 0;i<keahlian_array.length;i++){
            Chip chip = new Chip(view.getContext());
            chip.setText(keahlian_array[i]);
            chip.setId(i);
            chip.setCheckable(true);
            chip.setTextAppearanceResource(R.style.chipText);
            chip.setChipBackgroundColorResource(R.color.bg_chip_color);
            chip.setChipStrokeColorResource(R.color.stroke_chip_color);
            chip.setChipStrokeWidth(2);
            chipgroup.addView(chip);
        }
    }
    private void updateLabel(){
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

        dateedittext.setText(sdf.format(myCalendar.getTime()));
    }

    public void addTask(){
        database = FirebaseDatabase.getInstance();
        User = FirebaseAuth.getInstance().getCurrentUser();
        mRef = database.getReference("bantuan");
        Bantuan newbantuan = new Bantuan(User.getUid(),namaUser,judul,deskripsi,keahlian,idTalent,deadline,fee,tanggal,dateNow);
        mRef.push().setValue(newbantuan);
    }

    public String getDateNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c);
    }
    public String getUserName(){
        final String[] username = {""};
        mUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username[0] = dataSnapshot.child("nama").toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(getTag(),"Error retrieving username");
            }
        });
        return username[0];
    }
//    public String getChildValue(DatabaseReference reference, String child){
//        DatabaseReference ref = reference.child(child);
//        String data;
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                data = dataSnapshot.getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
