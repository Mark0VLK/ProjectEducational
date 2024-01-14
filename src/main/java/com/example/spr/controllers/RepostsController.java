
package com.example.spr.controllers;
import com.example.spr.models.Person;
import com.example.spr.models.Reposts;
import com.example.spr.repositories.PeopleRepository;
import com.example.spr.services.RepostsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Optional;

@Controller
public class RepostsController {

    private final RepostsService repostService;
    private final PeopleRepository peopleRepository;

    public RepostsController(RepostsService repostService, PeopleRepository peopleRepository) {
        this.repostService = repostService;
        this.peopleRepository = peopleRepository;
    }

    @PostMapping("/reposts/{postId}")
    public String repostPost(@PathVariable int postId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person user = person.get();

        repostService.repostPost(postId, user);
        return "redirect:/news";
    }

    @GetMapping("/reposts")

    public String showReposts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Person> person = peopleRepository.findByUsername(auth.getName());
        Person user = person.get();

        List<Reposts> reposts = repostService.getRepostsByUser(user);

        model.addAttribute("reposts", reposts);
        return "reposts";
    }
}