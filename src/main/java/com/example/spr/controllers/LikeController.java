package com.example.spr.controllers;


import com.example.spr.models.Likes;
import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.repositories.LikeRepository;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.repositories.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LikeController {


    private final PeopleRepository peopleRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public LikeController(PeopleRepository peopleRepository, PostRepository postRepository, LikeRepository likeRepository) {
        this.peopleRepository = peopleRepository;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    //Добавление лайка
    @PostMapping("/likes/{postId}")
    public String likePost(@PathVariable int postId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person user = person.get();
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            // Проверка, не лайкнул ли пользователь уже этот пост
            if (!post.isLikedByUser(user)) {
                Likes like = new Likes();
                like.setPerson(user);
                like.setPost(post);

                post.getLikesList().add(like);
                postRepository.save(post);

            }


        }
        return "redirect:/news";
    }
}
