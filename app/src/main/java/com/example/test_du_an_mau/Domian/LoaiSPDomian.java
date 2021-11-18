package com.example.test_du_an_mau.Domian;

public class LoaiSPDomian {

    private int resourceId;
    private String title;

    public LoaiSPDomian(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
