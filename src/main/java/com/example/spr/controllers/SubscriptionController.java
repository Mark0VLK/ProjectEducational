package com.example.spr.controllers;

import com.example.spr.models.Person;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


    //Отображает всех зарегестрированных пользователей из БД
    @GetMapping("/persons")
    public String getAllPerson(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> pers = peopleRepository.findByUsername(auth.getName());
        Person currentUser = pers.get();
        model.addAttribute("persons", personDetailsService.getAllPerson());
        model.addAttribute("isCurrentUser", currentUser);
        model.addAttribute("userChannel", currentUser);

        return "persons";
    }

    //Реализация подписки на пользователей
    @GetMapping("subscribe/{personId}")
    public String subscribe(@PathVariable int personId) {
        Person person = personDetailsService.findById(personId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> pers = peopleRepository.findByUsername(auth.getName());
        Person currentUser = pers.get();
        Optional<Person> current = peopleRepository.findByUsername(currentUser.getUsername());
        current.ifPresent(currentPerson ->
                personDetailsService.subscribe(currentUser, person)

        );

        return "redirect:/persons";
    }

    //Реализация отписки от пользователей
    @GetMapping("unsubscribe/{personId}")
    public String unSubscribe(@PathVariable int personId) {
        Person person = personDetailsService.findById(personId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> pers = peopleRepository.findByUsername(auth.getName());
        Person currentUser = pers.get();
        Optional<Person> current = peopleRepository.findByUsername(currentUser.getUsername());
        current.ifPresent(currentPerson ->
                personDetailsService.unsubscribe(currentUser, person)

        );
        return "redirect:/persons";
    }
}
