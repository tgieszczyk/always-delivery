package com.ahold.alwaysdeliver.ride.service;

import com.ahold.alwaysdeliver.api.exceptions.ValidationException;
import com.ahold.alwaysdeliver.api.payload.ErrorCode;
import com.ahold.alwaysdeliver.api.payload.JsonError;
import com.ahold.alwaysdeliver.locker.service.LockerService;
import com.ahold.alwaysdeliver.ride.payload.Ride;
import com.ahold.alwaysdeliver.ride.payload.RideReservationStatus;
import com.ahold.alwaysdeliver.ride.repository.RideReactiveRepository;
import com.ahold.alwaysdeliver.ride.repository.RideRepository;
import com.ahold.alwaysdeliver.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private RideReactiveRepository rideReactiveRepository;
    @Autowired
    private LockerService lockerService;
    @Autowired
    private UserService userService;

    public Mono<Ride> bookRide(ObjectId rideId) {
        String key = "bookRide" + rideId.toHexString();
        if (!lockerService.isLocked(key)) {
            lockerService.lock(key);
            if (lockerService.isActiveKey(key)) {
                try {
                    Optional<Ride> ride = rideRepository.findById(rideId);
                    if (ride.isPresent()) {
                        Ride result = ride.get();
                        if (result.isBooked()) {
                            throw new ValidationException(JsonError.error(ErrorCode.ERR0003, rideId.toHexString()));
                        }
                        result.setStatus(RideReservationStatus.APPROVED);
                        result.setDriverId(userService.getLoggedInUserContext().getId());
                        rideRepository.save(result);
                    }
                } finally {
                    lockerService.releaseLock(key);
                }
            }
        }
        return rideReactiveRepository.findById(rideId);
    }

    public void cancelRide(ObjectId rideId) {
        Optional<Ride> ride = rideRepository.findById(rideId);
        if (ride.isPresent()) {
            Ride result = ride.get();
            result.setDriverId(null);
            result.setStatus(null);
            rideRepository.save(result);
        }
    }
}
