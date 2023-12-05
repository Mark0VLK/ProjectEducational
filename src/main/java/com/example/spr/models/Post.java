package com.example.spr.models;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "textPost")
    private String textPost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person person;

    public Post() {
    }

    public Post(int id, String textPost) {
        this.id = id;
        this.textPost = textPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
