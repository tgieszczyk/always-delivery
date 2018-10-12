package com.ahold.alwaysdeliver.location.api;

import com.ahold.alwaysdeliver.api.CommonController;
import com.ahold.alwaysdeliver.location.payload.Location;
import com.ahold.alwaysdeliver.location.repository.LocationReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/locations")
public class LocationController extends CommonController {
    @Autowired
    private LocationReactiveRepository locationRepository;

    @GetMapping("/all")
    public Flux<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
