package com.example.spr.controllers;

import com.example.spr.models.Person;
import com.example.spr.services.RegistrationService;
import com.example.spr.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService reg;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService reg) {
        this.personValidator = personValidator;
        this.reg = reg;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult b) {
        personValidator.validate(person, b);

        if(b.hasErrors())
            return "/auth/registration";

        reg.register(person);
        return "redirect:/auth/login";
    }


}
