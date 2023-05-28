package com.demo.flink.model;

import com.demo.flink.com.demo.flink.data.LocationFactory;
import com.demo.flink.constant.LocationTheme;
import lombok.*;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static com.demo.flink.constant.Constants.REGULAR_LOCATIONS;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaxiBooking {

    private String ride_id;
    private Date pickupDateTime;
    private String pickupLocation;
    private String dropOffLocation;


    public static TaxiBooking newRandomBooking() {
        String randomPickupLocation = randomPickupLocation(LocationTheme.REGULAR);
        return new TaxiBooking(UUID.randomUUID().toString(), new Date(), randomPickupLocation, randomDropOffLocation(randomPickupLocation, LocationTheme.REGULAR));
    }

    public static TaxiBooking newRandomBooking(LocationTheme locationTheme) {
        String randomPickupLocation = randomPickupLocation(locationTheme);
        return new TaxiBooking(UUID.randomUUID().toString(), new Date(), randomPickupLocation, randomDropOffLocation(randomPickupLocation, locationTheme));
    }

    private static String randomPickupLocation(LocationTheme locationTheme) {
        return LocationFactory.getLocation(locationTheme).get(new Random().nextInt(LocationFactory.getLocation(locationTheme).size()));
    }


    private static String randomDropOffLocation(String pickupLocation, LocationTheme locationTheme) {
        String dropOffLocation = randomPickupLocation(locationTheme);
        if (dropOffLocation.equals(pickupLocation)) return randomDropOffLocation(pickupLocation, locationTheme);
        else return dropOffLocation;

    }


}

