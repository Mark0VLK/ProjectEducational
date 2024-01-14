package com.example.spr.models;

import javax.persistence.*;


@Entity
@Table(name = "likes")

public class Likes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "person_id",
            updatable = false,
            nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "post_id",
            updatable = false,
            nullable = false)
    private Post post;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }



}



