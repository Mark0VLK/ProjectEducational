package com.example.spr.services;

import com.example.spr.models.Person;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new PersonDetails(person.get());
    }


    public List<Person> getAllPerson(){
        return peopleRepository.findAll();
    }


    public void subscribe(Person currentUser, Person person) {
        person.getSubscribers().add(currentUser);
        peopleRepository.save(person);
    }

    public void unsubscribe(Person currentUser, Person person) {
        person.getSubscribers().remove(currentUser);
        peopleRepository.save(person);
    }
}
