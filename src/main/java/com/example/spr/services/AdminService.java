package com.example.spr.services;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adminStuff(){
        System.out.println("Only Admin");
    }
}
