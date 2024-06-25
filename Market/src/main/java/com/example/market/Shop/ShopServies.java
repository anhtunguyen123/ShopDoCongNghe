package com.example.market.Shop;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ShopServies {

    ShopDatabase shopDatabase = new ShopDatabase();

    public ShopServies() throws SQLException {
    }

    public String AddShop(Shop shop) {
        return shopDatabase.AddShop(shop);
    }

    public String checkShop(String maND){
        return shopDatabase.checkShop(maND);
    }
}
