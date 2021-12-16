package com.example.test_du_an_mau.Domian;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamDomian implements Serializable {

    private String MaSP, MaNguoiDung, LoaiHinhSP, LoaiSP, LoaiChiTietSP, DonVi,
    GioiHanViTri, NoiSanXuat, MoTaChiTiet, HanSuDung, GiaBan, SoDienThoai;
    private int SoLuong, Quyen;
    private List<String> AlbumAnh;
    private Date NgayThem = new Date();

    public SanPhamDomian() {

    }

    public SanPhamDomian(String maSP, String maNguoiDung, String loaiHinhSP, String loaiSP,
                         String loaiChiTietSP, String donVi, String gioiHanViTri, String noiSanXuat,
                         String moTaChiTiet, String hanSuDung, String giaBan, String soDienThoai,
                         int soLuong, int quyen, List<String> albumAnh, Date ngayThem) {
        MaSP = maSP;
        MaNguoiDung = maNguoiDung;
        LoaiHinhSP = loaiHinhSP;
        LoaiSP = loaiSP;
        LoaiChiTietSP = loaiChiTietSP;
        DonVi = donVi;
        GioiHanViTri = gioiHanViTri;
        NoiSanXuat = noiSanXuat;
        MoTaChiTiet = moTaChiTiet;
        HanSuDung = hanSuDung;
        GiaBan = giaBan;
        SoDienThoai = soDienThoai;
        SoLuong = soLuong;
        Quyen = quyen;
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

    public String getHanSuDung() {
        return HanSuDung;
    }

    public void setHanSuDung(String hanSuDung) {
        HanSuDung = hanSuDung;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getQuyen() {
        return Quyen;
    }

    public void setQuyen(int quyen) {
        Quyen = quyen;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> resuft = new HashMap<>();
        resuft.put("albumAnh", AlbumAnh);
        resuft.put("donVi", DonVi);
        resuft.put("giaBan", GiaBan);
        resuft.put("gioiHanViTri", GioiHanViTri);
        resuft.put("hanSuDung", HanSuDung);
        resuft.put("loaiChiTietSP", LoaiChiTietSP);
        resuft.put("loaiHinhSP", LoaiHinhSP);
        resuft.put("loaiSP", LoaiSP);
        resuft.put("maNguoiDung", MaNguoiDung);
        resuft.put("maSP", MaSP);
        resuft.put("moTaChiTiet", MoTaChiTiet);
        resuft.put("ngayThem", NgayThem);
        resuft.put("noiSanXuat", NoiSanXuat);
        resuft.put("quyen", Quyen);
        resuft.put("soDienThoai", SoDienThoai);
        resuft.put("soLuong", SoLuong);
        return resuft;
    }

}
