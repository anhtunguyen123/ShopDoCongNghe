package com.example.market.SanPham;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(path = "api/SanPham")
public class SanPhamController {

    private final SanPhamServies sanPhamServies;

    public SanPhamController(SanPhamServies sanPhamServies) {
        this.sanPhamServies = sanPhamServies;
    }

    @GetMapping
    public List<SanPham> getAllSP(){
        return sanPhamServies.getAllSanPham();
    }

    @PostMapping
    public String CRUDSP(@RequestBody SanPham sanPham){
        if(sanPhamServies.coSP(sanPham.getMaSP()) && sanPham.getMaSP() != null){
            if(sanPham.getSoLuongSP().matches("delete")){
                return sanPhamServies.xoaSP(sanPham.getMaSP());
            }
            return sanPhamServies.suaSP(sanPham);
        }
        return sanPhamServies.themSP(sanPham);
    }

    @RequestMapping(method = RequestMethod.POST,path = "/getAllSP")
    public List<SanPham> getAllSP(@RequestBody String maShop){
        return sanPhamServies.getAllSanPham(maShop);
    }

}
