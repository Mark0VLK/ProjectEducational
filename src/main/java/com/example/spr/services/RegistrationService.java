package com.example.spr.services;

import com.example.spr.models.Person;
import com.example.spr.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;

    @Autowired

    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    @Transactional
    public void register(Person person){
        peopleRepository.save(person);
    }
}
