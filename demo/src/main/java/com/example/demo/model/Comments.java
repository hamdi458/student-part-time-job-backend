package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String comment;
    private int note;
    private String companyname;
    private LocalDate commdate;
    @Column(nullable=false)
    private  String stuudentid;
    @Column(nullable=false)
    private String companyid;

    public Comments() {
        this.setCommdate(LocalDate.now());
    }

    public Comments(long id, String comment, int note, String companyname, LocalDate commdate, String stuudentid, String companyid) {
        this.id = id;
        this.comment = comment;
        this.note = note;
        this.companyname = companyname;
        this.commdate = LocalDate.now();
        this.stuudentid = stuudentid;
        this.companyid = companyid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getStuudentid() {
        return stuudentid;
    }

    public void setStuudentid(String stuudentid) {
        this.stuudentid = stuudentid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public LocalDate getCommdate() {
        return commdate;
    }

    public void setCommdate(LocalDate commdate) {
        this.commdate = commdate;
    }


}
