package com.example.spr.controllers;

import com.example.spr.models.Comment;
import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.models.Tweet;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.repositories.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CommentController {
    private final PostRepository postRepository;
    private final PeopleRepository peopleRepository;

    public CommentController(PostRepository postRepository, PeopleRepository peopleRepository) {
        this.postRepository = postRepository;
        this.peopleRepository = peopleRepository;
    }

    //Добавление комментария
    @PostMapping("/posts/{postId}")
    public String commentPost(@PathVariable int postId, @ModelAttribute Tweet content) {
        String con = content.getContent();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person user = person.get();
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Comment comment = new Comment();
            comment.setContent(con);
            comment.setPerson(user);
            comment.setPost(post);
            post.getComments().add(comment);
            postRepository.save(post);

        }

        return "redirect:/news";
    }
}
