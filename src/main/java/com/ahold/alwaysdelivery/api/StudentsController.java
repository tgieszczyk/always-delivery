package com.ahold.alwaysdelivery.api;

import com.ahold.alwaysdelivery.repository.Student;
import com.ahold.alwaysdelivery.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentsController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
