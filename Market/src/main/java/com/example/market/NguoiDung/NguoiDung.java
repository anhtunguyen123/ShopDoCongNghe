package com.example.market.NguoiDung;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NguoiDung {
    @Id
    private String maND;
    private String tenTK;
    private String tenDN;
    private String matkhau;
    private String soDT;
    private String diaChi;

    public NguoiDung(String tenTK, String tenDN, String matkhau, String soDT, String diaChi) {
        this.tenTK = tenTK;
        this.tenDN = tenDN;
        this.matkhau = matkhau;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    public NguoiDung(String maND, String tenTK, String tenDN, String matkhau, String soDT, String diaChi) {
        this.maND = maND;
        this.tenTK = tenTK;
        this.tenDN = tenDN;
        this.matkhau = matkhau;
        this.soDT = soDT;
        this.diaChi = diaChi;
    }

    public NguoiDung() {

    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaND() {
        return maND;
    }
}
