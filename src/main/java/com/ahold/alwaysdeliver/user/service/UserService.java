package com.ahold.alwaysdeliver.user.service;

import com.ahold.alwaysdeliver.user.payload.User;
import com.ahold.alwaysdeliver.user.repository.UserReactiveRepository;
import com.ahold.alwaysdeliver.user.repository.UserRepository;
import com.ahold.alwaysdeliver.utils.GenericBuilder;
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


    public User getLoggedInUserContext() {
        User user;
        if (userRepository.existsById(loggedInUserId)) {
            return userRepository.findById(loggedInUserId).get();
        }
        userRepository.save(user = LOGGED_IN_USER);

        return user;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
