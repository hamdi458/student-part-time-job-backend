package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "basephoto")
public class BasePhoto implements Serializable {
    @Id

    private long id;
    @Lob
    private byte[] photo;
    /*************new****/
    private String photoName;

    private String photoType;



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





    public BasePhoto(long id) {
        this.id = id;
    }

    public BasePhoto(long id, byte[] photo, String photoName, String photoType) {
        this.id = id;
        this.photo = photo;
        this.photoName = photoName;
        this.photoType = photoType;

    }

    public BasePhoto() {
    }
}
