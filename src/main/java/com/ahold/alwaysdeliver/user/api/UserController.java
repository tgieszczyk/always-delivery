package com.ahold.alwaysdeliver.user.api;

import com.ahold.alwaysdeliver.api.CommonController;
import com.ahold.alwaysdeliver.api.payload.ErrorCode;
import com.ahold.alwaysdeliver.api.validators.ObjectIdValidator;
import com.ahold.alwaysdeliver.user.payload.User;
import com.ahold.alwaysdeliver.user.repository.UserReactiveRepository;
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
public class UserController extends CommonController {
    @Autowired
    private UserReactiveRepository userRepository;

    @GetMapping("/all")
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Mono<User> findById(@PathVariable("userId") String userIdStr) {
        ObjectId userId = ObjectIdValidator.getValidatedObjectId(userIdStr, ErrorCode.ERR0002, userIdStr);
        return userRepository.findById(userId);
    }
}
