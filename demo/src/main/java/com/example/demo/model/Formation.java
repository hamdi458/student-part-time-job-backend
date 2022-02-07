package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private LocalDate datestart;
    private LocalDate dateend;
    private String poste;
    private LocalDate commdate;
    @Column(nullable=false)
    private String studintcinn;

    public Formation(long id, String name, String description, LocalDate datestart, LocalDate dateend, String poste, String studintcinn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datestart = datestart;
        this.dateend = dateend;
        this.poste = poste;
        this.studintcinn = studintcinn;
    }

    public Formation(long id, String name, String description, LocalDate datestart, LocalDate dateend, String studintcinn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datestart = datestart;
        this.dateend = dateend;
        this.studintcinn = studintcinn;
    }

    public Formation() {
        this.commdate = LocalDate.now();
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

    public void setDateend(LocalDate dateend) {
        this.dateend = dateend;
    }

    public String getStudintcin() {
        return studintcinn;
    }

    public void setStudintcin(String studintcin) {
        this.studintcinn = studintcin;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public LocalDate getCommdate() {
        return commdate;
    }

    public void setCommdate(LocalDate commdate) {
        this.commdate = commdate;
    }

    public Formation(long id, String name, String description, LocalDate datestart, LocalDate dateend, String poste, LocalDate commdate, String studintcinn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datestart = datestart;
        this.dateend = dateend;
        this.poste = poste;
        this.commdate = LocalDate.now();
        this.studintcinn = studintcinn;
    }
}
