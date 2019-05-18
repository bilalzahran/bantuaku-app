package com.team9.bantuaku.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nama;
    private String email;
    private String no_telp;
    private String password;
    private int point;
    private int selesai;
    private List<String> keahlian = new ArrayList<>();
    private  String biografi;

    public User(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.no_telp = "Silahkan isi nomor telepon anda";
        keahlian.add("Belum memiliki keahlian");
        this.point = 0;
        this.selesai = 0;
        this.biografi = "Anda belum memperkenalkan diri anda";
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getPassword() {
        return password;
    }

    public int getPoint() {
        return point;
    }

    public int getSelesai() {
        return selesai;
    }

    public List<String> getKeahlian() {
        return keahlian;
    }

    public String getBiografi() {
        return biografi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setSelesai(int selesai) {
        this.selesai = selesai;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian.add(keahlian);
    }

    public void setBiografi(String biografi) {
        this.biografi = biografi;
    }
}
