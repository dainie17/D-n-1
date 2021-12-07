package com.example.test_du_an_mau.Domian;

import com.google.firebase.database.ServerValue;

public class Comment {
    private String content, uid, uimg, username;
    private Object time;

    public Comment() {
    }

    public Comment(String content, String uid, String uimg, String username) {
        this.content = content;
        this.uid = uid;
        this.uimg = uimg;
        this.username = username;
        this.time = ServerValue.TIMESTAMP;
    }

    public Comment(String content, String uid, String uimg, String username, Object time) {
        this.content = content;
        this.uid = uid;
        this.uimg = uimg;
        this.username = username;
        this.time = time;
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

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }
}
