package com.ahold.allwaysdelivery.user.service;

import com.ahold.allwaysdelivery.user.payload.User;
import com.ahold.allwaysdelivery.user.repository.UserReactiveRepository;
import com.ahold.allwaysdelivery.user.repository.UserRepository;
import com.ahold.allwaysdelivery.utils.GenericBuilder;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    private static final ObjectId loggedInUserId = new ObjectId("000000000000000000000001");
    private static final User LOGGED_IN_USER = GenericBuilder.of(User::new)
            .with(User::setApproved, true)
            .with(User::setEmail, "student@study.nl")
            .with(User::setName, "Very")
            .with(User::setSurname, "Interested")
            .with(User::setId, loggedInUserId)
            .with(User::setBirthDate, LocalDate.of(1981, 9, 22))
            .with(User::setPhone, "CallMeMaybe")
            .build();

    @Autowired
    private UserReactiveRepository userReactiveRepository;
    @Autowired
    private UserRepository userRepository;

    private ThreadLocal<User> loggedInUserContext = new ThreadLocal<>();

    public User getLoggedInUserContext() {
        User user = loggedInUserContext.get();
        if (user == null && userRepository.existsById(loggedInUserId)) {
            loggedInUserContext.set(user = userRepository.findById(loggedInUserId).get());
        } else if (user == null) {
            userRepository.save(user = LOGGED_IN_USER);
            loggedInUserContext.set(user);
        }
        return user;
    }
}
