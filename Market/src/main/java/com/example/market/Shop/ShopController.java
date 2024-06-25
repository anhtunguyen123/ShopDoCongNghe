package com.example.market.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(path = "api/Shop")
public class ShopController {

    private final ShopServies shopServies;

    @Autowired
    public ShopController(ShopServies shopServies) {
        this.shopServies = shopServies;
    }

    @PostMapping
    public String addShop(@RequestBody Shop shop) {
        return shopServies.AddShop(shop);
    }

    @PostMapping(path = "/check")
    public String checkShop(@RequestBody String maND){
        return shopServies.checkShop(maND);
    }

    // CORS
    @RequestMapping(method = RequestMethod.OPTIONS)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity optionTest() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
