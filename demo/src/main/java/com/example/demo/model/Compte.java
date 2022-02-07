package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "compte")
public class Compte {
    @Id
    private String email;

    private String password;
    @Column(nullable=false)
    private String name;
    private String lastname;
    @JsonIgnoreProperties
    private int type;
    private Integer verif;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Compte(String email, String password, String name, int type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
    }

    public Compte(String email, String password, int type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Compte() {
    }

    public Compte(String email, String password, String name, String lastname, int type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.type = type;
    }

    public Integer getVerif() {
        return verif;
    }

    public void setVerif(Integer verif) {
        this.verif = verif;
    }

    public Compte(String email, String password, String name, String lastname, int type, Integer verif) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.type = type;
        this.verif = verif;
    }
}
