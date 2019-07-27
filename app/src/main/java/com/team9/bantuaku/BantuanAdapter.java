package com.team9.bantuaku;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;
import com.team9.bantuaku.Helper.ImageHelper;
import com.team9.bantuaku.Helper.KeahlianHelper;
import com.team9.bantuaku.Model.Bantuan;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.ViewHolder> {
    private List<Bantuan> listBantuan = new ArrayList<>();
    private OnBantuanListener listener;
    private ImageHelper imageHelper = new ImageHelper();
    private KeahlianHelper keahlianHelper = new KeahlianHelper();

    public static final String firebaseStorageUrl = "gs://bantuaku-team9.appspot.com/";

    public void setData(List<Bantuan> listBantuan){
        this.listBantuan = listBantuan;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BantuanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bantuan_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BantuanAdapter.ViewHolder holder, int position) {
        Bantuan bantuan = listBantuan.get(position);
        FirebaseStorage mStorage = FirebaseStorage.getInstance();
        holder.tv_nama.setText(bantuan.getFirstNameUser());
        holder.tv_judul_bantuan.setText(bantuan.getJudul());
        holder.tv_tanggal.setText(bantuan.getDeadline());
        holder.tv_fee.setText(String.valueOf(bantuan.getBayaran()));
        imageHelper.setProfileImage(mStorage,bantuan.getIdUser(),holder.iv_profileImage);
        keahlianHelper.setKeahlian(holder.fb_keahlian.getContext(), bantuan.getKeahlian(), holder.fb_keahlian);
    }

    @Override
    public int getItemCount() {
        return listBantuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_nama,tv_judul_bantuan,tv_tanggal,tv_fee;
        private CircleImageView iv_profileImage;
        private FlexboxLayout fb_keahlian;
        OnBantuanListener onBantuanListener;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_judul_bantuan = itemView.findViewById(R.id.tv_judul_bantuan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_fee = itemView.findViewById(R.id.tv_fee);
            iv_profileImage = itemView.findViewById(R.id.iv_profile_image);
            fb_keahlian = itemView.findViewById(R.id.fb_keahlian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(listBantuan.get(position));
                    }
                }
            });
        }
    }

    public interface OnBantuanListener{
        void onItemClick(Bantuan bantuan);
    }
    public void setOnBantuanClickListener(OnBantuanListener listener){
        this.listener = listener;
    }
}
