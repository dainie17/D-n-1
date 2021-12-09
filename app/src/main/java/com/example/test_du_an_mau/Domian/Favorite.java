package com.example.test_du_an_mau.Domian;

import java.util.HashMap;
import java.util.Map;

public class Favorite {

    int YeuThich;
    String idSanPham;

    public Favorite() {
    }

    public Favorite(int yeuThich, String idSanPham) {
        YeuThich = yeuThich;
        this.idSanPham = idSanPham;
    }

    public int getYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(int yeuThich) {
        YeuThich = yeuThich;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> resuft = new HashMap<>();
        resuft.put("yeuThich", YeuThich);
        resuft.put("idSanPham", idSanPham);
        return resuft;
    }

}
