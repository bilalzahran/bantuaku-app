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
    private List<Bantuan> listBantuan = new ArrayList<>();
    private OnBantuanListener onBantuanListener;

    public BantuanAdapter(OnBantuanListener onBantuanListener){
        this.onBantuanListener = onBantuanListener;
    }

    public void setData(List<Bantuan> listBantuan){
        this.listBantuan = listBantuan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BantuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bantuan_list, parent, false);
        return new ViewHolder(view,onBantuanListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BantuanAdapter.ViewHolder holder, int position) {
        Bantuan bantuan = listBantuan.get(position);

        String rp = "Rp.";
        holder.tv_nama.setText(bantuan.getFirstNameUser());
        holder.tv_judul_bantuan.setText(bantuan.getJudul());
        holder.tv_tanggal.setText(bantuan.getDeadline());
        holder.tv_fee.setText(String.valueOf(bantuan.getBayaran()));
    }

    @Override
    public int getItemCount() {
        return listBantuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_nama,tv_judul_bantuan,tv_tanggal,tv_fee;
        OnBantuanListener onBantuanListener;
        public ViewHolder(@NonNull View itemView, OnBantuanListener onBantuanListener){
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_judul_bantuan = itemView.findViewById(R.id.tv_judul_bantuan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_fee = itemView.findViewById(R.id.tv_fee);
            this.onBantuanListener = onBantuanListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onBantuanListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnBantuanListener{
        void onItemClick(int position);
    }

}
