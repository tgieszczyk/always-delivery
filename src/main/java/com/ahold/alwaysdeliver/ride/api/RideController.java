package com.ahold.alwaysdeliver.ride.api;

import com.ahold.alwaysdeliver.api.CommonController;
import com.ahold.alwaysdeliver.api.payload.ErrorCode;
import com.ahold.alwaysdeliver.api.validators.ObjectIdValidator;
import com.ahold.alwaysdeliver.ride.payload.Ride;
import com.ahold.alwaysdeliver.ride.repository.RideReactiveRepository;
import com.ahold.alwaysdeliver.ride.service.RideService;
import com.ahold.alwaysdeliver.user.payload.User;
import com.ahold.alwaysdeliver.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rides")
public class RideController extends CommonController {
    @Autowired
    private RideReactiveRepository rideRepository;
    @Autowired
    private RideService rideService;
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

    @PostMapping("/book/{rideId}")
    public Mono<Ride> bookRide(@PathVariable("rideId") String rideIdStr) {
        ObjectId rideId = ObjectIdValidator.getValidatedObjectId(rideIdStr, ErrorCode.ERR0002, rideIdStr);
        return rideService.bookRide(rideId);
    }

    @DeleteMapping("/cancel/{rideId}")
    public void cancelRide(@PathVariable("rideId") String rideIdStr) {
        ObjectId rideId = ObjectIdValidator.getValidatedObjectId(rideIdStr, ErrorCode.ERR0002, rideIdStr);
        rideService.cancelRide(rideId);
    }


}
