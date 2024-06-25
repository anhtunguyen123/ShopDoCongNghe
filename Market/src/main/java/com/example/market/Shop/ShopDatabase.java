package com.example.market.Shop;

import com.example.market.Connection;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ShopDatabase extends Connection {
    java.sql.Connection conn = ds.getConnection();
    Statement statement = conn.createStatement();
    ResultSet rs;

    public ShopDatabase() throws SQLException {
        super();
    }

    public String AddShop(Shop shop) {
        if (checkTenShop(shop.getTenShop())) {
            if (checkSDT(shop.getSoDTShop())) {
                String sql = String.format("INSERT INTO Shop Values('%s','%s','%s','%s')",
                        shop.getTenShop(), shop.getSoDTShop(), shop.getDiaChiShop(),
                        shop.getMaND());
                try {
                    int row = statement.executeUpdate(sql);
                    return String.valueOf(row);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return "falseSDT";
            }
        } else {
            return "falseTenShop";
        }
    }

    private Boolean checkTenShop(String tenShop) {
        String sql = String.format("Select maShop from Shop Where tenShop = '%s'", tenShop);
        try {
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Boolean checkSDT(String sdtShop) {
        String sql = String.format("Select maShop from Shop Where soDTShop = '%s'", sdtShop);
        try {
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String checkShop(String maND){
        String sql = String.format("Select maShop from Shop where maND = %s",Integer.parseInt(maND.substring(1,maND.length()-1)));
        try{
            rs = statement.executeQuery(sql);
            if(rs.next()){
                return rs.getString(1);
            }
            return "0";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
