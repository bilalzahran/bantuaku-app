package com.team9.bantuaku;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.zip.Inflater;

public class DialogAddTask extends BottomSheetDialogFragment {
    private AddTaskDialogListener listener;
    private Button btn_kirim,btn_batal;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bottomsheet_task, container, false);
        btn_kirim = (Button)view.findViewById(R.id.btn_kirim);
        btn_batal = (Button)view.findViewById(R.id.btn_batal);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonKirimClicked();
                dismiss();
            }
        });

        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
    public interface AddTaskDialogListener {
        void onButtonKirimClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (AddTaskDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            +" Class must be implement AddTaskDialogListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
