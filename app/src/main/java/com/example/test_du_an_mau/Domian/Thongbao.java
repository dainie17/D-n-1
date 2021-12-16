package com.example.test_du_an_mau.Domian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Thongbao {

    String IDThongBao, LoaiThongBao, NoiDung, LinkAnh, IDNguoiNhan;

    public Thongbao() {
    }

    public Thongbao(String IDThongBao, String loaiThongBao, String noiDung, String linkAnh, String IDNguoiNhan) {
        this.IDThongBao = IDThongBao;
        LoaiThongBao = loaiThongBao;
        NoiDung = noiDung;
        LinkAnh = linkAnh;
        this.IDNguoiNhan = IDNguoiNhan;
    }

    public String getIDThongBao() {
        return IDThongBao;
    }

    public void setIDThongBao(String IDThongBao) {
        this.IDThongBao = IDThongBao;
    }

    public String getLoaiThongBao() {
        return LoaiThongBao;
    }

    public void setLoaiThongBao(String loaiThongBao) {
        LoaiThongBao = loaiThongBao;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getIDNguoiNhan() {
        return IDNguoiNhan;
    }

    public void setIDNguoiNhan(String IDNguoiNhan) {
        this.IDNguoiNhan = IDNguoiNhan;
    }
}
