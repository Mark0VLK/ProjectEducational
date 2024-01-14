package com.example.spr.services;

import com.example.spr.models.Post;
import com.example.spr.repositories.PostRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(int postId) {
        Optional<Post> post = postRepository.findById(postId);

        return post.get();

    }

}
