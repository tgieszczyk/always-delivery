package com.ahold.allwaysdelivery.utils.mongobee.changelog;

import com.ahold.allwaysdelivery.location.payload.Location;
import com.ahold.allwaysdelivery.location.repository.LocationRepository;
import com.ahold.allwaysdelivery.mongobee.changeset.ChangeSet;
import com.ahold.allwaysdelivery.ride.payload.Ride;
import com.ahold.allwaysdelivery.ride.repository.RideRepository;
import com.ahold.allwaysdelivery.user.service.UserService;
import com.ahold.allwaysdelivery.utils.ApplicationContextProvider;
import com.ahold.allwaysdelivery.utils.GenericBuilder;

import java.time.LocalTime;
import java.util.Arrays;

@com.ahold.allwaysdelivery.mongobee.changeset.ChangeLog(order = "0001")
public class DbChangeLog {
    @ChangeSet(author = "tgieszczyk", id = "0001", order = "0001")
    public void createFewUsers() {
        UserService userService = ApplicationContextProvider.getApplicationContext().getBean(UserService.class);
        userService.getLoggedInUserContext();
    }

    @ChangeSet(author = "tgieszczyk", id = "0003", order = "0003")
    public void createLocations() {
        LocationRepository locationRepository = ApplicationContextProvider.getApplicationContext().getBean(LocationRepository.class);
        locationRepository.deleteAll();

        RideRepository rideRepository = ApplicationContextProvider.getApplicationContext().getBean(RideRepository.class);

        Location location1 = locationRepository.save(GenericBuilder.of(Location::new)
                    .with(Location::setAddress, "Limburglaan 2")
                    .with(Location::setPlace, "Eindhoven")
                    .with(Location::setZone, "South")
                .build());
        addRidesToLocation(rideRepository, location1, GenericBuilder.of(Ride::new)
                .with(Ride::setTitle, "Title 1")
                .with(Ride::setTitle, "foo bar")
                .with(Ride::setFrom, LocalTime.of(11, 0))
                .with(Ride::setTill, LocalTime.of(14, 30))
                .with(Ride::setBonus, true)
                .with(Ride::setFee, 34.),
                GenericBuilder.of(Ride::new)
                .with(Ride::setTitle, "Title 2")
                .with(Ride::setTitle, "foo foo")
                .with(Ride::setFrom, LocalTime.of(10, 0))
                .with(Ride::setTill, LocalTime.of(18, 0))
                .with(Ride::setBonus, false)
                .with(Ride::setFee, 60.22));


        Location location2 = locationRepository.save(GenericBuilder.of(Location::new)
                    .with(Location::setAddress, "Gelderlandplein 47")
                    .with(Location::setPlace, "Amsterdam")
                    .with(Location::setZone, "North West")

                .build());
        /*"rides": [
          {
            "title": "Title 3",
            "date": "foo",
            "from": "13.30",
            "till": "16:00",
            "bonus": true,
            "fee": 20
          },
          {
            "title": "Title 3",
            "date": "foo",
            "from": "9.30",
            "till": "10:00",
            "bonus": false,
            "fee": 24
          }
        ]*/
        addRidesToLocation(rideRepository, location2, GenericBuilder.of(Ride::new)
                        .with(Ride::setTitle, "Title 3")
                        .with(Ride::setTitle, "foo")
                        .with(Ride::setFrom, LocalTime.of(13, 30))
                        .with(Ride::setTill, LocalTime.of(16, 0))
                        .with(Ride::setBonus, true)
                        .with(Ride::setFee, 20.),
                GenericBuilder.of(Ride::new)
                        .with(Ride::setTitle, "Title 3")
                        .with(Ride::setTitle, "foo")
                        .with(Ride::setFrom, LocalTime.of(9, 30))
                        .with(Ride::setTill, LocalTime.of(10, 0))
                        .with(Ride::setBonus, false)
                        .with(Ride::setFee, 24.));

        Location location3 = locationRepository.save(GenericBuilder.of(Location::new)
                    .with(Location::setAddress, "Argonautenweg 1")
                    .with(Location::setPlace, "Rotterdam")
                    .with(Location::setZone, "South West")

                .build());
/*"rides": [
          {
            "title": "Title 3",
            "date": "foo",
            "from": "9.30",
            "till": "10:00",
            "bonus": false,
            "fee": 34.2
          },
          {
            "title": "Title 3",
            "date": "foo",
            "from": "14.00",
            "till": "19:00",
            "bonus": false,
            "fee": 24.2
          }
        ]*/
        addRidesToLocation(rideRepository, location3, GenericBuilder.of(Ride::new)
                        .with(Ride::setTitle, "Title 3")
                        .with(Ride::setTitle, "foo")
                        .with(Ride::setFrom, LocalTime.of(9, 30))
                        .with(Ride::setTill, LocalTime.of(10, 0))
                        .with(Ride::setBonus, false)
                        .with(Ride::setFee, 34.2),
                GenericBuilder.of(Ride::new)
                        .with(Ride::setTitle, "Title 3")
                        .with(Ride::setTitle, "foo")
                        .with(Ride::setFrom, LocalTime.of(14, 0))
                        .with(Ride::setTill, LocalTime.of(19, 0))
                        .with(Ride::setBonus, false)
                        .with(Ride::setFee, 24.2));
    }

    private void addRidesToLocation(RideRepository rideRepository, Location location, GenericBuilder<Ride>... rideBuilders) {
        Arrays.stream(rideBuilders).forEach(b -> {
            Ride r = b.build();
            r.setLocationId(location.getId());
            rideRepository.save(r);
        });
    }
}
