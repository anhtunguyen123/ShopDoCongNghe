package com.example.market.GioHang;

import com.example.market.SanPham.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GioHangServies {
    private final GioHangDatabase gioHangDatabase = new GioHangDatabase();

    @Autowired
    public GioHangServies() throws SQLException {
    }

    public List<SanPham> getallGioHang(String maND){
            return gioHangDatabase.getAllGioHang(maND);
        }

    public String xoaSanPham (GioHang gioHang){
        return  gioHangDatabase.xoaSanPham(gioHang);
    }

    public String themVaoGioHang (GioHang gioHang){
        return  gioHangDatabase.themVaoGioHang(gioHang);
    }
}
