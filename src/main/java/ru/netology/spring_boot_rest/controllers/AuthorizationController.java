package ru.netology.spring_boot_rest.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.spring_boot_rest.exceptions.InvalidCredentials;
import ru.netology.spring_boot_rest.exceptions.UnauthorizedUser;
import ru.netology.spring_boot_rest.model.Authorities;
import ru.netology.spring_boot_rest.services.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String name,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(name, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<String> handleIC(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<String> handleUU(UnauthorizedUser e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
