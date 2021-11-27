package com.example.test_du_an_mau.Domian;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SanPhamDomian implements Serializable {

    private String MaSP, MaNguoiDung, LoaiHinhSP, LoaiSP, LoaiChiTietSP, DonVi,
    GioiHanViTri, NoiSanXuat, MoTaChiTiet, HanSuDung;
    private int SoLuong;
    private List<String> AlbumAnh;
    private Date NgayThem = new Date();

    public SanPhamDomian() {

    }

    public SanPhamDomian(String maSP, String maNguoiDung, String loaiHinhSP,
                         String loaiSP, String loaiChiTietSP, String donVi,
                         String gioiHanViTri, String noiSanXuat, String moTaChiTiet,
                         int soLuong, String hanSuDung, List<String> albumAnh, Date ngayThem) {

        MaSP = maSP;
        MaNguoiDung = maNguoiDung;
        LoaiHinhSP = loaiHinhSP;
        LoaiSP = loaiSP;
        LoaiChiTietSP = loaiChiTietSP;
        DonVi = donVi;
        GioiHanViTri = gioiHanViTri;
        NoiSanXuat = noiSanXuat;
        MoTaChiTiet = moTaChiTiet;
        SoLuong = soLuong;
        HanSuDung = hanSuDung;
        AlbumAnh = albumAnh;
        NgayThem = ngayThem;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    public String getLoaiHinhSP() {
        return LoaiHinhSP;
    }

    public void setLoaiHinhSP(String loaiHinhSP) {
        LoaiHinhSP = loaiHinhSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        LoaiSP = loaiSP;
    }

    public String getLoaiChiTietSP() {
        return LoaiChiTietSP;
    }

    public void setLoaiChiTietSP(String loaiChiTietSP) {
        LoaiChiTietSP = loaiChiTietSP;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        DonVi = donVi;
    }

    public String getGioiHanViTri() {
        return GioiHanViTri;
    }

    public void setGioiHanViTri(String gioiHanViTri) {
        GioiHanViTri = gioiHanViTri;
    }

    public String getNoiSanXuat() {
        return NoiSanXuat;
    }

    public void setNoiSanXuat(String noiSanXuat) {
        NoiSanXuat = noiSanXuat;
    }

    public String getMoTaChiTiet() {
        return MoTaChiTiet;
    }

    public void setMoTaChiTiet(String moTaChiTiet) {
        MoTaChiTiet = moTaChiTiet;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        HanSuDung = hanSuDung;
    }

    public List<String> getAlbumAnh() {
        return AlbumAnh;
    }

    public void setAlbumAnh(List<String> albumAnh) {
        AlbumAnh = albumAnh;
    }

    public Date getNgayThem() {
        return NgayThem;
    }

    public void setNgayThem(Date ngayThem) {
        NgayThem = ngayThem;
    }
}
