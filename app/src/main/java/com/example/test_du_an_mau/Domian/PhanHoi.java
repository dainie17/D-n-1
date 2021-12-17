package com.example.test_du_an_mau.Domian;

public class PhanHoi {

    String id, tenNguoiGui, linkHinh, Noidung;

    public PhanHoi() {
    }

    public PhanHoi(String id, String tenNguoiGui, String linkHinh, String noidung) {
        this.id = id;
        this.tenNguoiGui = tenNguoiGui;
        this.linkHinh = linkHinh;
        Noidung = noidung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNguoiGui() {
        return tenNguoiGui;
    }

    public void setTenNguoiGui(String tenNguoiGui) {
        this.tenNguoiGui = tenNguoiGui;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }
}
