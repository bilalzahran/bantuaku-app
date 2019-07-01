package com.team9.bantuaku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team9.bantuaku.Model.Bantuan;

import java.util.ArrayList;
import java.util.List;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.ViewHolder> {
    private Context context;
    private List<Bantuan> listBantuan;

    public BantuanAdapter(Context context){
        this.context = context;
        listBantuan = new ArrayList<>();
    }

    public void setData(List<Bantuan> listBantuan){
        this.listBantuan = listBantuan;
    }

    @NonNull
    @Override
    public BantuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bantuan_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BantuanAdapter.ViewHolder holder, int position) {
        Bantuan bantuan = listBantuan.get(position);

        holder.tv_nama.setText(bantuan.getFirstNameUser());
        holder.tv_judul_bantuan.setText(bantuan.getJudul());
        holder.tv_tanggal.setText(bantuan.getDeadline());
        holder.tv_fee.setText("Rp."+ bantuan.getBayaran().toString());
    }

    @Override
    public int getItemCount() {
        return listBantuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nama,tv_judul_bantuan,tv_tanggal,tv_fee;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_judul_bantuan = itemView.findViewById(R.id.tv_judul_bantuan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_fee = itemView.findViewById(R.id.tv_fee);
        }
    }
}
