package com.example.spr.controllers;

import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.models.Tweet;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Controller
public class BlogController {
    private final PostRepository postRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BlogController(PostRepository postRepository, PeopleRepository peopleRepository) {
        this.postRepository = postRepository;
        this.peopleRepository = peopleRepository;
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("posts.content", "");
        return "blog-add";
    }

    @PostMapping("/blog")

    public String blogPostAdd(@ModelAttribute Tweet posts, @RequestParam("file") MultipartFile file) throws IOException {
        String content = posts.getContent();
        Post post = new Post(content);
        post.setPost_text(content);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        post.setPerson(person.get());
        post.setPhoto_name(file.getOriginalFilename());
        post.setPhoto_post(file.getBytes());
        postRepository.save(post);
        return "redirect:/hello";
    }


    @GetMapping("/blog/read")
    public String blogRead(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person person1 = person.get();
        Iterable<Post> post = person1.getPostsPerson();
        ArrayList<Post> res = new ArrayList<>();
        post.forEach(res::add);



        res.forEach(tweet -> {
            byte[] photoBytes = tweet.getPhoto_post();
            String base64Image = Base64.getEncoder().encodeToString(photoBytes);
            tweet.setPhoto_post(base64Image.getBytes());
        });

        model.addAttribute("post", res);

        return "blog-read";
    }


}

