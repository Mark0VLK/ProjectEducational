package com.example.spr.repositories;

import com.example.spr.models.Person;
import com.example.spr.models.Reposts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepostsRepository extends JpaRepository<Reposts, Integer> {


    List<Reposts> findByPerson(Person user);
}
