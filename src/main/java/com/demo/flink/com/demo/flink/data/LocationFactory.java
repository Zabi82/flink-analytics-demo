package com.demo.flink.com.demo.flink.data;

import com.demo.flink.constant.Constants;
import com.demo.flink.constant.LocationTheme;
import scala.collection.immutable.Stream;

import java.util.List;

public class LocationFactory {

    public static List<String> getLocation(LocationTheme locationTheme) {
        List<String> locations = Constants.REGULAR_LOCATIONS;
        switch (locationTheme) {
            case REGULAR:
                locations = Constants.REGULAR_LOCATIONS;
                break;
            case NORMAL_WEEKEND:
                locations = Constants.NORMAL_WEEKEND_LOCATIONS;
                break;
            case LONG_WEEKEND:
                locations = Constants.LONG_WEEKEND_LOCATIONS;
                break;
            case FRIDAY_EVENING:
                locations = Constants.FRIDAY_EVENING_LOCATIONS;
                break;
        }
        return locations;
    }
}
