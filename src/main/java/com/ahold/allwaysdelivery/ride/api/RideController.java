package com.ahold.allwaysdelivery.ride.api;

import com.ahold.allwaysdelivery.api.payload.ErrorCode;
import com.ahold.allwaysdelivery.api.validators.ObjectIdValidator;
import com.ahold.allwaysdelivery.ride.payload.Ride;
import com.ahold.allwaysdelivery.ride.repository.RideRepository;
import com.ahold.allwaysdelivery.user.payload.User;
import com.ahold.allwaysdelivery.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rides")
public class RideController {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{rideId}")
    public Mono<Ride> findById(@PathVariable("rideId") String rideIdStr) {
        ObjectId rideId = ObjectIdValidator.getValidatedObjectId(rideIdStr, ErrorCode.ERR0002, rideIdStr);
        return rideRepository.findById(rideId);
    }

    @GetMapping("/all")
    public Flux<Ride> findAll() {
        return rideRepository.findAll();
    }

    @GetMapping("/open")
    public Flux<Ride> findAllOpen() {
        return rideRepository.findAllByDriverIdIsNull();
    }

    @GetMapping("/open/{locationId}")
    public Flux<Ride> findAllOpenForLocation(@PathVariable("locationId") String locationIdStr) {
        ObjectId locationId = ObjectIdValidator.getValidatedObjectId(locationIdStr, ErrorCode.ERR0002, locationIdStr);
        return rideRepository.findAllByLocationIdAndDriverIdIsNull(locationId);
    }
    @GetMapping("/myrides")
    public Flux<Ride> findAllMyRides() {
        User loggedInUser = userService.getLoggedInUserContext();
        return rideRepository.findAllByDriverId(loggedInUser.getId());
    }


}
