package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "icon")
public class icon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Lob
    private byte[] icon;
    /*************new****/
    private String iconName;

    private String iconType;
    private String companyid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public icon() {
    }

    public icon(long id, byte[] icon, String iconName, String iconType, String companyid) {
        this.id = id;
        this.icon = icon;
        this.iconName = iconName;
        this.iconType = iconType;
        this.companyid = companyid;
    }

    public icon(byte[] icon, String companyid) {
        this.icon = icon;
        this.companyid = companyid;
    }
}
