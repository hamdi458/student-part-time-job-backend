package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "studentjob")
public class Student {
    @Id
    private String cin;
    private String firstname;
    private String lastname;
    private LocalDate datebirth;
    private String nationality;
    private String city;
    private  long tel = 0;
    private String phone;
    private long views;
    private String descripion;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "studintcin")
    private List<skills> studentskills;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studintcinn")
    private List<Formation> studentformations;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studintcinnn")
    private List<Experience> studentexperience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "studentid")
    private Photo studentPhoto;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studintcinnnn")
    private List<React> studentreact;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "stuudentid")
    private List<Comments> commentandnote;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public List<Comments> getCommentandnote() {
        return commentandnote;
    }

    public void setCommentandnote(List<Comments> commentandnote) {
        this.commentandnote = commentandnote;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public LocalDate getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(LocalDate datebirth) {
        this.datebirth = datebirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<skills> getStudentskills() {
        return studentskills;
    }

    public void setStudentskills(List<skills> studentskills) {
        this.studentskills = studentskills;
    }

    public List<Formation> getStudentformations() {
        return studentformations;
    }

    public void setStudentformations(List<Formation> studentformations) {
        this.studentformations = studentformations;
    }

    public List<Experience> getStudentexperience() {
        return studentexperience;
    }

    public void setStudentexperience(List<Experience> studentexperience) {
        this.studentexperience = studentexperience;
    }

    public Photo getStudentPhoto() {
        return studentPhoto;
    }

    public void setStudentPhoto(Photo studentPhoto) {
        this.studentPhoto = studentPhoto;
    }

    public List<React> getStudentreact() {
        return studentreact;
    }

    public void setStudentreact(List<React> studentreact) {
        this.studentreact = studentreact;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public Student(String cin, String firstname, String lastname, LocalDate datebirth, String nationality, String city) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datebirth = datebirth;
        this.nationality = nationality;
        this.city = city;
    }

    public Student() {
    }

    public Student(String cin, String firstname, String lastname, LocalDate datebirth, String nationality, String city, List<skills> studentskills, List<Formation> studentformations, List<Experience> studentexperience) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datebirth = datebirth;
        this.nationality = nationality;
        this.city = city;
        this.studentskills = studentskills;
        this.studentformations = studentformations;
        this.studentexperience = studentexperience;
    }

    public Student(String cin, String firstname, String lastname, LocalDate datebirth, String nationality, String city, List<skills> studentskills, List<Formation> studentformations, List<Experience> studentexperience, Photo studentPhoto) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datebirth = datebirth;
        this.nationality = nationality;
        this.city = city;
        this.studentskills = studentskills;
        this.studentformations = studentformations;
        this.studentexperience = studentexperience;
        this.studentPhoto = studentPhoto;
    }



    public Student(String cin, String firstname, String lastname, LocalDate datebirth, String nationality, String city, long tel) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datebirth = datebirth;
        this.nationality = nationality;
        this.city = city;
        this.tel = tel;
    }

    public Student(String cin) {
        this.cin = cin;
    }

    public Student(String cin, String firstname) {
        this.cin = cin;
        this.firstname = firstname;
    }

    public Student(String cin, String firstname, String lastname) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public Student(String cin, String firstname, String lastname, LocalDate datebirth, String nationality, String city, String phone, long views, String descripion) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.datebirth = datebirth;
        this.nationality = nationality;
        this.city = city;
        this.phone = phone;
        this.views = views;
        this.descripion = descripion;
    }
}
