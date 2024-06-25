package com.example.market.Shop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Shop {

    @Id
    private String maShop;
    private String tenShop;
    private String soDTShop;
    private String diaChiShop;
    private String maND;

    public Shop(String maShop, String tenShop, String soDTShop, String diaChiShop, String maND) {
        this.maShop = maShop;
        this.tenShop = tenShop;
        this.soDTShop = soDTShop;
        this.diaChiShop = diaChiShop;
        this.maND = maND;
    }

    public Shop(String tenShop, String soDTShop, String diaChiShop, String maND) {
        this.tenShop = tenShop;
        this.soDTShop = soDTShop;
        this.diaChiShop = diaChiShop;
        this.maND = maND;
    }

    public Shop() {

    }


    public String getTenShop() {
        return tenShop;
    }

    public void setTenShop(String tenShop) {
        this.tenShop = tenShop;
    }

    public String getSoDTShop() {
        return soDTShop;
    }

    public void setSoDTShop(String soDTShop) {
        this.soDTShop = soDTShop;
    }

    public String getDiaChiShop() {
        return diaChiShop;
    }

    public void setDiaChiShop(String diaChiShop) {
        this.diaChiShop = diaChiShop;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public void setMaShop(String maShop) {
        this.maShop = maShop;
    }

    public String getMaShop() {
        return maShop;
    }
}
