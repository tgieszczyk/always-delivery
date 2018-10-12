package com.ahold.allwaysdelivery.utils.mongobee.changelog;

import com.ahold.allwaysdelivery.location.payload.Location;
import com.ahold.allwaysdelivery.location.repository.LocationRepository;
import com.ahold.allwaysdelivery.mongobee.changeset.ChangeSet;
import com.ahold.allwaysdelivery.user.service.UserService;
import com.ahold.allwaysdelivery.utils.ApplicationContextProvider;
import com.ahold.allwaysdelivery.utils.GenericBuilder;

@com.ahold.allwaysdelivery.mongobee.changeset.ChangeLog(order = "0001")
public class DbChangeLog {
    @ChangeSet(author = "tgieszczyk", id = "0001", order = "0001")
    public void createFewUsers() {
        UserService userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class);
        userService.getLoggedInUserContext();
    }

    @ChangeSet(author = "tgieszczyk", id = "0002", order = "0002")
    public void createLocations() {
        LocationRepository locationRepository = ApplicationContextProvider.getApplicationContext().getBean(LocationRepository.class);

        locationRepository.save(GenericBuilder.of(Location::new)
                .with(Location::setAddress, "Some addresss 1")
                .with(Location::setPlace, "Place 1")
                .with(Location::setZone, "South east zone 1")

                .build());
        locationRepository.save(GenericBuilder.of(Location::new)
                .with(Location::setAddress, "Some addresss 2")
                .with(Location::setPlace, "Place 2")
                .with(Location::setZone, "South zone 2")

                .build());
        locationRepository.save(GenericBuilder.of(Location::new)
                .with(Location::setAddress, "Some addresss 3")
                .with(Location::setPlace, "Place 3")
                .with(Location::setZone, "North West zone 3")

                .build());
    }
}
