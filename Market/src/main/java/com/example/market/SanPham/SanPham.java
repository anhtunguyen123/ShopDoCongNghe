package com.example.market.SanPham;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SanPham {

    @Id
    private String maSP;
    private String tenSP;
    private String maShop;
    private String giaSP;
    private String loaiSP;
    private String soLuongSP;

    public SanPham(String maSP, String tenSP, String maShop, String giaSP, String loaiSP, String soLuongSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maShop = maShop;
        this.giaSP = giaSP;
        this.loaiSP = loaiSP;
        this.soLuongSP = soLuongSP;
    }

    public SanPham(String tenSP, String maShop, String giaSP, String loaiSP, String soLuongSP) {
        this.tenSP = tenSP;
        this.maShop = maShop;
        this.giaSP = giaSP;
        this.loaiSP = loaiSP;
        this.soLuongSP = soLuongSP;
    }

    public SanPham() {
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMaShop() {
        return maShop;
    }

    public void setMaShop(String maShop) {
        this.maShop = maShop;
    }

    public String getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(String giaSP) {
        this.giaSP = giaSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(String soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaSP() {
        return maSP;
    }
}
