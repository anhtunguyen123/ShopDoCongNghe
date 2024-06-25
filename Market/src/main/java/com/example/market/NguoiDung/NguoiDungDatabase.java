package com.example.market.NguoiDung;

import com.example.market.Connection;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NguoiDungDatabase extends Connection {
    java.sql.Connection conn = ds.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rs;


    public NguoiDungDatabase() throws SQLException {
        super();
    }

    public NguoiDung getUserDetails(String maND) {
        NguoiDung user = null;
        String sql = "SELECT maND, tenTK, tenDN, matkhau FROM NguoiDung WHERE maND = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, maND);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new NguoiDung();
                    user.setMaND(rs.getString("maND"));
                    user.setTenTK(rs.getString("tenTK"));
                    user.setTenDN(rs.getString("tenDN"));
                    user.setMatkhau(rs.getString("matkhau"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public NguoiDung Login(String username, String password) {
        String sql = String.format("SELECT maND FROM NguoiDung WHERE tenDN = '%s' AND matkhau = '%s'", username, password);
        try {
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                String maND = rs.getString(1);
                return getUserDetails(maND); // Gọi hàm lấy chi tiết người dùng
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String Register(NguoiDung nguoiDung) {
        if(checkTenDN(nguoiDung.getTenDN())){
            if(checkSDT(nguoiDung.getSoDT())){
                do{
                    String sql = String.format("INSERT INTO NguoiDung Values('%s','%s','%s','%s','%s')"
                            , nguoiDung.getTenTK(), nguoiDung.getTenDN(), nguoiDung.getMatkhau()
                            , nguoiDung.getSoDT(), nguoiDung.getDiaChi());
                    try {
                        int row = statement.executeUpdate(sql);
                        return String.valueOf(row);
                    } catch (SQLException e) {
                        nguoiDung.setMaND(nguoiDung.getMaND().replace(nguoiDung.getMaND().substring(nguoiDung.getMaND().length()-1),String.valueOf(Integer.parseInt(nguoiDung.getMaND().substring(nguoiDung.getMaND().length()-1))+1)));
                    }
                }while(true);

            }
            else{
                return "falseSDT";
            }
        }
        else{
            return "falseND";
        }
    }

    private Boolean checkTenDN(String tenDN){
        String sql = String.format("Select maND from NguoiDung Where tenDN = '%s'",tenDN);
        try{
            rs = statement.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean checkSDT(String soDT){
        String sql = String.format("Select maND from NguoiDung Where soDT = '%s'",soDT);
        try{
            rs = statement.executeQuery(sql);
            if(rs.next()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}