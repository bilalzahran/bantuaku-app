package com.team9.bantuaku.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nama;
    private String no_telp;
    private int point;
    private int selesai;
    private List<String> keahlian = new ArrayList<>();
    private  String biografi;
    private  String foto;

    public User(){

    }
    public User(String nama, String no_telp, List<String> keahlian, String foto, String biografi){
        this.nama = nama;
        this.no_telp = no_telp;
        this.keahlian = keahlian;
        this.foto = foto;
        this.biografi = biografi;
    }

    public User(String nama, String no_telp) {
        this.nama = nama;
        this.no_telp = no_telp;
        keahlian.add("Belum memiliki keahlian");
        this.point = 0;
        this.selesai = 0;
        this.foto = " ";
        this.biografi = "Anda belum memperkenalkan diri anda";
    }

    public String getNama() {
        return nama;
    }

    public String getNo_telp() {
        return no_telp;
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

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setSelesai(int selesai) {
        this.selesai = selesai;
    }

    public void setKeahlian(List<String> keahlian) {
        this.keahlian = keahlian;
    }

    public void setBiografi(String biografi) {
        this.biografi = biografi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
