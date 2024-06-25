package com.example.market.GioHang;

import com.example.market.SanPham.SanPham;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(path = "api/GioHang")
public class GioHangController {
    private final GioHangServies gioHangServies;

    public GioHangController(GioHangServies gioHangServies) {
        this.gioHangServies = gioHangServies;
    }

    @GetMapping
    public List<SanPham> getallGioHang( String maND) {
        return gioHangServies.getallGioHang(maND);
    }
    @PostMapping
    public String themVaoGioHang (@RequestBody GioHang gioHang){
        return  gioHangServies.themVaoGioHang(gioHang);
    }

    @DeleteMapping
    public String xoaSanPham (@RequestBody GioHang gioHang){
        return  gioHangServies.xoaSanPham(gioHang);
    }

    //CROS !important
    @RequestMapping(method = RequestMethod.OPTIONS)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity optionTest(){
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
