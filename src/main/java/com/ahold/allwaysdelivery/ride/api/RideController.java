package com.ahold.allwaysdelivery.ride.api;

import com.ahold.allwaysdelivery.api.CommonController;
import com.ahold.allwaysdelivery.api.payload.ErrorCode;
import com.ahold.allwaysdelivery.api.validators.ObjectIdValidator;
import com.ahold.allwaysdelivery.ride.payload.Ride;
import com.ahold.allwaysdelivery.ride.repository.RideReactiveRepository;
import com.ahold.allwaysdelivery.ride.service.RideService;
import com.ahold.allwaysdelivery.user.payload.User;
import com.ahold.allwaysdelivery.user.service.UserService;
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
