package com.example.spr.models;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id")
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

    @Column(name = "content")
    private String content;

    public Comment(String con) {
    }
    public Comment() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
