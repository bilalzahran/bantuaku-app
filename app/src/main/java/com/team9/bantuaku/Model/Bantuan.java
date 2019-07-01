package com.team9.bantuaku.Model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class Bantuan {
    private String namaUser;
    private String idUser;
    private String judul;
    private String deskripsi;
    private List<String> keahlian = new ArrayList<>();
    private List<String> idTalent = new ArrayList<>();
    private String deadline;
    private Integer bayaran;
    private String tanggal;

    public Bantuan(){

    }
    public Bantuan(String idUser, String namaUser, String judul, String deskripsi, List<String> keahlian, List<String> idTalent, String deadline, Integer bayaran, String tanggal) {
        this.namaUser = namaUser;
        this.idUser = idUser;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.keahlian = keahlian;
        this.idTalent = idTalent;
        this.deadline = deadline;
        this.bayaran = bayaran;
        this.tanggal = tanggal;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<String> getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(List<String> keahlian) {
        this.keahlian = keahlian;
    }

    public List<String> getIdTalent() {
        return idTalent;
    }

    public void setIdTalent(List<String> idTalent) {
        this.idTalent = idTalent;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getBayaran() {
        return bayaran;
    }

    public void setBayaran(Integer bayaran) {
        this.bayaran = bayaran;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getFirstNameUser(){
        String firstName;
        firstName = namaUser.substring(0, namaUser.indexOf(" "));
        return firstName;
    }
}
