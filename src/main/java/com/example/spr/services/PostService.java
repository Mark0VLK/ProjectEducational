package com.example.spr.services;

import com.example.spr.models.Comment;
import com.example.spr.models.Like;
import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.repositories.LikeRepository;
import com.example.spr.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    private LikeRepository likeRepository;

    public PostService(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post findById(int postId) {
        Optional<Post> post = postRepository.findById(postId);

        return post.get();

    }
}
