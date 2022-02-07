package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "company")
public class Company {
    @Id
    private String id;
    private String name;
    private String description;
    private String adress;
    private String city;
    private String phone;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companyid")
    private List<Post> postes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "icon_id", referencedColumnName = "companyid")
    private icon companyicon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public icon getCompanyicon() {
        return companyicon;
    }

    public void setCompanyicon(icon companyicon) {
        this.companyicon = companyicon;
    }

    public List<Post> getPostes() {
        return postes;
    }

    public void setPostes(List<Post> postes) {
        this.postes = postes;
    }

    public Company(String id, String name, String description, String adress, String city, List<Post> postes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.postes = postes;
    }

    public Company(String id, String name, String description, String adress, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.city = city;
    }

    public Company() {
    }

    public Company(String id, String name, String description, String adress, String city, icon companyicon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.companyicon = companyicon;
    }

    public Company(String id, String name, String description, String adress, String city, List<Post> postes, icon companyicon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.postes = postes;
        this.companyicon = companyicon;
    }

    public Company(String id) {
        this.id = id;
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(String id, String name, String description, String adress, String city, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
