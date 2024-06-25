package com.example.market.GioHang;

import com.example.market.Connection;
import com.example.market.SanPham.SanPham;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GioHangDatabase extends Connection {
    java.sql.Connection cnn = ds.getConnection();
    Statement statement = cnn.createStatement();
    ResultSet rs;
    PreparedStatement ps;

    public GioHangDatabase() throws SQLException {
        super();
    }

    public List<SanPham> getAllGioHang(String maND) {
        List<SanPham> gioHang = new ArrayList<>();
        String sql = "SELECT SP.maSP, SP.tenSP, SP.giaSP, SP.soLuongSP " +
                "FROM GioHang GH " +
                "JOIN SanPham SP ON GH.maSP = SP.maSP " +
                "WHERE GH.maND = ?";
        try {
            ps = cnn.prepareStatement(sql);
            ps.setString(1, maND);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGiaSP(rs.getString("giaSP"));
                sp.setSoLuongSP(rs.getString("soLuongSP"));
                gioHang.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gioHang;
    }

    public String themVaoGioHang(GioHang gioHang) {
        String sql = String.format("insert into GioHang values('%s','%s')",
                gioHang.getMaND(),gioHang.getMaSP());
        int row = 0;
        try {
            row = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(row);
    }

    public String xoaSanPham(GioHang gioHang) {
        String sql = String.format("DELETE FROM GioHang WHERE maND='%s' AND maSP='%s'",
                gioHang.getMaND(), gioHang.getMaSP());
        int row = 0;
        try {
            row = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(row);
    }
}
