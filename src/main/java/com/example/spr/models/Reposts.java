package com.example.spr.models;

import javax.persistence.*;

@Entity
@Table(name = "reposts")
public class Reposts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    public Reposts() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
