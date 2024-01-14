package com.example.spr.services;

import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.models.Reposts;
import com.example.spr.repositories.PostRepository;
import com.example.spr.repositories.RepostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepostsService {


    private RepostsRepository repostsRepository;
    private PostRepository postRepository;

    public RepostsService(RepostsRepository repostsRepository, PostRepository postRepository) {
        this.repostsRepository = repostsRepository;
        this.postRepository = postRepository;
    }

    public void repostPost(int postId, Person user) {
        // Проверяем, существует ли пост с указанным postId
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post originalPost = postOptional.get();

            // Проверяем, не репостил ли уже текущий пользователь этот пост
            if (!originalPost.isRepostedByUser(user)) {
                // Создаем новый репост
                Reposts repost = new Reposts();
                repost.setPerson(user);
                repost.setPost(originalPost);
                // Сохраняем репост в репозитории
                repostsRepository.save(repost);
            }
        }
    }

    public List<Reposts> getRepostsByUser(Person user) {
        return repostsRepository.findByPerson(user);
    }
}

