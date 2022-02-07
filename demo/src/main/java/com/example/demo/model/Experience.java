package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String post;
    private String description;
    private LocalDate datestart;
    private LocalDate dateend;

    @Column(nullable=false)
    private String studintcinnn;

    public long getId() {
        return id;
    }

    public Experience(long id, String name, String description, LocalDate datestart, LocalDate dateent, String studintcinnn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datestart = datestart;
        this.dateend = dateend;
        this.studintcinnn = studintcinnn;
    }

    public Experience(long id, String name, String post, String description, LocalDate datestart, LocalDate dateend, String studintcinnn) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.description = description;
        this.datestart = datestart;
        this.dateend = dateend;
        this.studintcinnn = studintcinnn;
    }

    public void setId(long id) {
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

    public LocalDate getDatestart() {
        return datestart;
    }

    public void setDatestart(LocalDate datestart) {
        this.datestart = datestart;
    }

    public LocalDate getDateend() {
        return dateend;
    }

    public void setDateend(LocalDate dateent) {
        this.dateend = dateent;
    }

    public String getStudintcin() {
        return studintcinnn;
    }

    public Experience() {
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setStudintcin(String studintcin) {
        this.studintcinnn = studintcin;
    }
}
