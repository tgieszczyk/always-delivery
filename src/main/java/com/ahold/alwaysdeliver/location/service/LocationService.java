package com.ahold.alwaysdeliver.location.service;

import com.ahold.alwaysdeliver.location.payload.Location;
import com.ahold.alwaysdeliver.location.repository.LocationReactiveRepository;
import com.ahold.alwaysdeliver.location.repository.LocationRepository;
import com.ahold.alwaysdeliver.user.payload.User;
import com.ahold.alwaysdeliver.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LocationService {
    @Autowired
    private LocationReactiveRepository locationReactiveRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserService userService;

    public Mono<Location> selectLocation(ObjectId locationId) {
        User loggedInUser = userService.getLoggedInUserContext();
        loggedInUser.setLocationId(locationId);
        userService.updateUser(loggedInUser);

        return locationReactiveRepository.findById(locationId);
    }
}
