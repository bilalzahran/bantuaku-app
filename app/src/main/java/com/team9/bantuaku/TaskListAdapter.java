package com.team9.bantuaku;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team9.bantuaku.Model.Bantuan;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    private Context mcontext;
    private List<Bantuan> listBantuan;

    public TaskListAdapter(Context context){
        this.mcontext = context;
        listBantuan = new ArrayList<>();
    }

    public void setData(List<Bantuan> listBantuan){
        this.listBantuan = listBantuan;
    }


    @NonNull
    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.task_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.ViewHolder viewHolder, int i) {
        Bantuan bantuan =listBantuan.get(i);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
