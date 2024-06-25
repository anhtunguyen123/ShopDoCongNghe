package com.example.market.GioHang;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class GioHang {
    @Id
    private String maGH;
    private String maND;
    private String maSP;


    public GioHang() {}

    public GioHang(String maGH, String maND, String maSP) {

        this.maSP = maSP;
        this.maGH = maGH;
        this.maND = maND;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }


}
