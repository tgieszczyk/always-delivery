package com.ahold.allwaysdelivery.user.api;

import com.ahold.allwaysdelivery.api.payload.ErrorCode;
import com.ahold.allwaysdelivery.api.validators.ObjectIdValidator;
import com.ahold.allwaysdelivery.user.payload.User;
import com.ahold.allwaysdelivery.user.repository.UserReactiveRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserReactiveRepository userRepository;

    @GetMapping("/")
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Mono<User> findById(@PathVariable("userId") String userIdStr) {
        ObjectId userId = ObjectIdValidator.getValidatedObjectId(userIdStr, ErrorCode.ERR0002, userIdStr);
        return userRepository.findById(userId);
    }
}
