package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Lob
    private byte[] photo;
    /*************new****/
    private String photoName;

    private String photoType;
    private String studentid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public Photo() {
    }

    public Photo(long id, byte[] photo, String photoName, String photoType, String studentid) {
        this.id = id;
        this.photo = photo;
        this.photoName = photoName;
        this.photoType = photoType;
        this.studentid = studentid;
    }

    public Photo(byte[] photo, String studentid) {
        this.photo = photo;
        this.studentid = studentid;
    }


    public Photo( byte[] photo, String photoName, String photoType, String studentid) {

        this.photo = photo;
        this.photoName = photoName;
        this.photoType = photoType;
        this.studentid = studentid;
    }
}


