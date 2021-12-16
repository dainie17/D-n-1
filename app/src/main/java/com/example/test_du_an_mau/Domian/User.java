package com.example.test_du_an_mau.Domian;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private String id;
    private String username;
    private String imageURL;
    private String status;
    private String search;
    private String Phone;
    private String DiaChi;
    private String Email;
    private String pass;
    private int loai;

    public User() {
    }

    public User(String id, String username, String imageURL, String status, String search, String phone, String diaChi, String email, String pass, int loai) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.Phone = phone;
        this.DiaChi = diaChi;
        this.Email = email;
        this.pass = pass;
        this.loai = loai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> resuft = new HashMap<>();
        resuft.put("id", id);
        resuft.put("imageURL", imageURL);
        resuft.put("loai", loai);
        resuft.put("search", search);
        resuft.put("status", status);
        resuft.put("phone", Phone);
        resuft.put("username", username);
        resuft.put("diaChi", DiaChi);
        resuft.put("email", Email);
        resuft.put("pass", pass);
        return resuft;
    }

}
