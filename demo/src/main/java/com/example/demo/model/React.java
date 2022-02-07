package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "react")
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable=false)
    private long postid;
    @Column(nullable=false)
    private String studintcinnnn;
    @Enumerated(EnumType.STRING)
    private Reacttype reactstate;
    private LocalDate ractdate = LocalDate.now();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public String getStudintcin() {
        return studintcinnnn;
    }

    public void setStudintcin(String studintcin) {
        this.studintcinnnn = studintcin;
    }

    public Reacttype getReactstate() {
        return reactstate;
    }

    public void setReactstate(Reacttype reactstate) {
        this.reactstate = reactstate;
    }

    public React() {
        this.reactstate = Reacttype.INTERESTED;
    }

    public React(long id, long postid, String studintcin, Reacttype reactstate) {
        this.id = id;
        this.postid = postid;
        this.studintcinnnn = studintcin;
        this.reactstate = Reacttype.INTERESTED;
    }
}
