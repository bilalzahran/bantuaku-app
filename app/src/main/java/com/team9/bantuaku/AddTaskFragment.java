package com.team9.bantuaku;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskFragment extends Fragment {
    EditText dateedittext;
    Calendar myCalendar;
    ChipGroup keahlian;
    DatePickerDialog.OnDateSetListener date;
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
        keahlian = (ChipGroup)view.findViewById(R.id.chip_keahlian);

        //Set Chip in Chipgroup keahlian
        String[] keahlian_array = getResources().getStringArray(R.array.keahlian);

        for(int i = 0;i<keahlian_array.length;i++){
            Chip chip = new Chip(view.getContext());
            chip.setText(keahlian_array[i]);
            chip.setCheckable(true);
            //chip.setTextColor(getResources().getColor(R.color.text_chip_color));
            chip.setTextAppearanceResource(R.style.chipText);
            chip.setChipBackgroundColorResource(R.color.bg_chip_color);
            chip.setChipStrokeColorResource(R.color.stroke_chip_color);
            chip.setChipStrokeWidth(2);
            keahlian.addView(chip);
        }

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(year,month,dayOfMonth);
                updateLabel();
            }
        };

        dateedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DATE))
                .show();
            }
        });

        return view;
    }
    private void setChip(ChipGroup chipgroup){

    }
    private void updateLabel(){
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

        dateedittext.setText(sdf.format(myCalendar.getTime()));
    }
}
