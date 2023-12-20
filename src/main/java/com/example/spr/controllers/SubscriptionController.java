package com.example.spr.controllers;

import com.example.spr.models.Person;
import com.example.spr.models.Post;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SubscriptionController {
    PeopleRepository peopleRepository;
    PersonDetailsService personDetailsService;

    @Autowired
    public SubscriptionController(PeopleRepository peopleRepository, PersonDetailsService personDetailsService) {
        this.peopleRepository = peopleRepository;
        this.personDetailsService = personDetailsService;
    }


    @GetMapping("/persons")
    public String getAllPerson(Model model, @PathVariable Person person) {
model.addAttribute("userChannel",person.getId());
       model.addAttribute("persons", personDetailsService.getAllPerson());
        return "persons";
    }


    @GetMapping("/subscribers")
    public String subscribersPerson(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person currentPerson = person.get();
        model.addAttribute("subscriptionsCount", currentPerson.getSubscriptions().size());
        model.addAttribute("subscribersCount", currentPerson.getSubscribers().size());
        model.addAttribute("isSubscriber", currentPerson.getSubscribers().contains(currentPerson));


        return "subscribers";
    }
}
