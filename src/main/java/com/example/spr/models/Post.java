package com.example.spr.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")

public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "post_text")
    private String post_text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id",
            updatable = false,
            nullable = false)
    private Person person;


    @Column(name = "post_photo")
    byte[] photo_post;

    @Column(name = "photo_name")
    String photo_name;

    @ManyToMany
    @JoinTable(
            name = "post",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> personList;



    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reposts> reposts = new ArrayList<>();

    @Transient
    private String base64Image;

    // Геттеры и сеттеры для base64Image
    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Post(String post_text) {
    }

    public Post(int id, String post_text, Person person) {
        this.id = id;
        this.post_text = post_text;
        this.person = person;
    }

    public Post() {

    }

    public Post(List<Person> personList) {
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_text() {
        return post_text;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public byte[] getPhoto_post() {
        return photo_post;
    }

    public void setPhoto_post(byte[] photo_post) {
        this.photo_post = photo_post;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public List<Likes> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Likes> likesList) {
        this.likesList = likesList;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isLikedByUser(Person person) {
        return likesList.stream().anyMatch(like -> like.getPerson().equals(person));
    }

    public List<Reposts> getReposts() {
        return reposts;
    }

    public void setReposts(List<Reposts> reposts) {
        this.reposts = reposts;
    }

    public boolean isRepostedByUser(Person user) {
        return reposts.stream().anyMatch(repost -> repost.getPerson().equals(user));

    }
}