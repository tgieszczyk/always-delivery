package com.ahold.alwaysdeliver.location.api;

import com.ahold.alwaysdeliver.api.CommonController;
import com.ahold.alwaysdeliver.api.exceptions.ValidationException;
import com.ahold.alwaysdeliver.api.payload.ErrorCode;
import com.ahold.alwaysdeliver.api.payload.JsonError;
import com.ahold.alwaysdeliver.api.validators.ObjectIdValidator;
import com.ahold.alwaysdeliver.location.payload.Location;
import com.ahold.alwaysdeliver.location.repository.LocationReactiveRepository;
import com.ahold.alwaysdeliver.location.service.LocationService;
import com.ahold.alwaysdeliver.user.payload.User;
import com.ahold.alwaysdeliver.user.service.UserService;
import com.mongodb.MongoNodeIsRecoveringException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/locations")
public class LocationController extends CommonController {
    @Autowired
    private LocationReactiveRepository locationRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Flux<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/{locationId}/select")
    public Mono<Location> selectLocation(@PathVariable("locationId") String locationIdStr) {
        ObjectId locationId = ObjectIdValidator.getValidatedObjectId(locationIdStr, ErrorCode.ERR0002, locationIdStr);
        return locationService.selectLocation(locationId);
    }
    @PostMapping("/{locationId}")
    public Mono<Location> findLocation(@PathVariable("locationId") String locationIdStr) {
        ObjectId locationId = ObjectIdValidator.getValidatedObjectId(locationIdStr, ErrorCode.ERR0002, locationIdStr);
        return locationRepository.findById(locationId);
    }
}
