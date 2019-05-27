package com.team9.bantuaku.Model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class Bantuan {
    private String idUser;
    private String judul;
    private String deskripsi;
    private List<String> keahlian = new ArrayList<>();
    private List<String> idTalent = new ArrayList<>();
    private String deadline;
    private Integer bayaran;
    private String tanggal;

    public Bantuan(String idUser, String judul, String deskripsi, List<String> keahlian, List<String> idTalent, String deadline, Integer bayaran, String tanggal) {
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

    public void setKeahlian(String keahlian) {
        this.keahlian.add(keahlian);
    }

    public List<String> getIdTalent() {
        return idTalent;
    }

    public void setIdTalent(String idTalent) {
        this.idTalent.add(idTalent);
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
}
