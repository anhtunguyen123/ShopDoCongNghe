package com.example.market.SanPham;

import com.example.market.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDatabase extends Connection {
    java.sql.Connection cnn = ds.getConnection();
    Statement statement = cnn.createStatement();
    ResultSet rs;

    public SanPhamDatabase() throws SQLException {
        super();
    }

    public List<SanPham> getAllSP(){
        List<SanPham> sps = new ArrayList<SanPham>();
        String sql = "Select maSP,tenSP,giaSP,soLuongSP from SanPham";
        try{
            rs = statement.executeQuery(sql);
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGiaSP(rs.getString("giaSP"));
                sp.setSoLuongSP(rs.getString("soLuongSP"));
                sps.add(sp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sps;
    }

    public List<SanPham> getAllSP(String maShop){
        List<SanPham> sps = new ArrayList<SanPham>();
        String sql = String.format("Select maSP,tenSP,giaSP,soLuongSP,loaiSP from SanPham where maShop = %s",Integer.parseInt(maShop.substring(1,maShop.length()-1)));
        try{
            rs = statement.executeQuery(sql);
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGiaSP(rs.getString("giaSP"));
                sp.setSoLuongSP(rs.getString("soLuongSP"));
                sp.setLoaiSP(rs.getString("loaiSP"));
                sps.add(sp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sps;
    }

    public boolean coSanPham(String maSP){
        String sql = String.format("Select maSP from SANPHAM WHERE maSP = %s",maSP);
        try{
            rs = statement.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String themSP(SanPham sanPham){
        String sql = String.format("INSERT INTO sanPham Values('%s','%s','%s','%s','%s')",
                sanPham.getTenSP(),sanPham.getMaShop(),sanPham.getGiaSP(),
                sanPham.getLoaiSP(),sanPham.getSoLuongSP());
        try{
            int row = statement.executeUpdate(sql);
            return String.valueOf(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String suaSP(SanPham newsanPham){
        String sql = String.format("UPDATE SANPHAM SET TENSP = '%s', GIASP = '%s', LoaiSP = '%s', SoLUongSP = '%s' WHERE maSP = '%s'",
                newsanPham.getTenSP(),newsanPham.getGiaSP(),newsanPham.getLoaiSP(),newsanPham.getSoLuongSP(),newsanPham.getMaSP());
        try{
            int row = statement.executeUpdate(sql);
            return  String.valueOf(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String xoaSP(String maSP){
        String sql = String.format("DELETE SANPHAM WHERE maSP = '%s'",maSP);
        try{
            int row = statement.executeUpdate(sql);
            return String.valueOf(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
