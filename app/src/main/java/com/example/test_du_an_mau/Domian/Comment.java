package com.example.test_du_an_mau.Domian;

import com.google.firebase.database.ServerValue;

import java.io.Serializable;

public class Comment implements Serializable {
    private String content, uid, idsp;

    public Comment() {
    }

    public Comment(String content, String uid, String idsp) {
        this.content = content;
        this.uid = uid;
        this.idsp = idsp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String uimg) {
        this.idsp = idsp;
    }
}
