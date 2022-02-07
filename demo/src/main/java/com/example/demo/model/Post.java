package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String type;
    @Column(nullable=false)
    private String companyid;
    private String companyname;
    private String description;
    private String adress;
    private String city;
    @Column(columnDefinition = "float default 0")
    private float  jobsalaire  = 0 ;
    private LocalDate postedate;
    private LocalDate datestart;
    private LocalDate dateend;
    private LocalTime starthour;
    private LocalTime endhour;
    @Enumerated(EnumType.STRING)
    private Poststate poststate;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "postid")
    private List<React> postreact;



    public List<React> getPostreact() {
        return postreact;
    }

    public void setPostreact(List<React> postreact) {
        this.postreact = postreact;
    }

    public long getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getPostedate() {
        return postedate;
    }

    public void setPostedate(LocalDate postedate) {
        this.postedate = postedate;
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

    public void setDateend(LocalDate dateend) {
        this.dateend = dateend;
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

    public float getSalaire() {
        return jobsalaire;
    }

    public void setSalaire(float salaire) {
        this.jobsalaire = salaire;
    }

    public LocalTime getStarthour() {
        return starthour;
    }

    public void setStarthour(LocalTime starthour) {
        this.starthour = starthour;
    }

    public LocalTime getEndhour() {
        return endhour;
    }

    public void setEndhour(LocalTime endhour) {
        this.endhour = endhour;
    }

    public Poststate getPoststate() {
        return poststate;
    }

    public void setPoststate(Poststate poststate) {
        this.poststate = poststate;
    }

    public Post(long id, String name, String type, String companyid, String companyname, String description, String adress, String city, float salaire, LocalDate postedate, LocalDate datestart, LocalDate dateend, LocalTime starthour, LocalTime endhour, Poststate poststate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.companyid = companyid;
        this.companyname = companyname;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.jobsalaire = salaire;
        this.postedate = postedate;
        this.datestart = datestart;
        this.dateend = dateend;
        this.starthour = starthour;
        this.endhour = endhour;
        this.poststate = Poststate.FREE;
    }

    public Post() {
        this.setPostedate(LocalDate.now());
        this.poststate = Poststate.FREE;
    }

    public Post(long id, String name, String type, LocalDate postedate, LocalDate datestart, LocalDate dateend, String companyid, String companyname, String description, String adress, String city, float salaire) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.postedate = LocalDate.now();
        this.datestart = datestart;
        this.dateend = dateend;
        this.companyid = companyid;
        this.companyname = companyname;
        this.description = description;
        this.adress = adress;
        this.city = city;
        this.jobsalaire = salaire;
    }
}
