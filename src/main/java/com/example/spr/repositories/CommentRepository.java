package com.example.spr.repositories;

import com.example.spr.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
