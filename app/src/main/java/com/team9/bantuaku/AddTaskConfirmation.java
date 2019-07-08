package com.team9.bantuaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTaskConfirmation extends AppCompatActivity {
    private Button btn_selesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_confirmation);
        btn_selesai = findViewById(R.id.btnSelesai);

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskConfirmation.this, TaskListFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
