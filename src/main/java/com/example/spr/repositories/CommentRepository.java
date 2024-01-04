package com.example.spr.repositories;

import com.example.spr.models.Comment;
import com.example.spr.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
