package com.example.spr.repositories;

import com.example.spr.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Integer> {

}
