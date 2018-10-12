package com.ahold.allwaysdelivery.location.api;

import com.ahold.allwaysdelivery.location.payload.Location;
import com.ahold.allwaysdelivery.location.repository.LocationReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationReactiveRepository locationRepository;

    @GetMapping("/all")
    public Flux<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
