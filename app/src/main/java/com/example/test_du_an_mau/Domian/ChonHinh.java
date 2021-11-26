package com.example.test_du_an_mau.Domian;

import android.net.Uri;

import java.io.Serializable;

public class ChonHinh implements Serializable {

    private String LinkHinhAnh;
    private Uri image;

    public ChonHinh() {
    }

    public ChonHinh(String linkHinhAnh, Uri image) {

        LinkHinhAnh = linkHinhAnh;
        this.image = image;

    }

    public String getLinkHinhAnh() {
        return LinkHinhAnh;
    }

    public void setLinkHinhAnh(String linkHinhAnh) {
        LinkHinhAnh = linkHinhAnh;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
