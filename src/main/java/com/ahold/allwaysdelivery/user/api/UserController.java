package com.ahold.allwaysdelivery.user.api;

import com.ahold.allwaysdelivery.user.payload.User;
import com.ahold.allwaysdelivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository studentRepository;

    @GetMapping("/")
    public Flux<User> getAllStudents() {
        return studentRepository.findAll();
    }
}
