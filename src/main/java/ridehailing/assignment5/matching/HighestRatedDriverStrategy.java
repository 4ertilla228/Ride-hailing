package main.java.ridehailing.assignment5.matching;

import java.util.Comparator;
import java.util.List;

public class HighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .max(Comparator.comparingDouble(Driver::rating))
                .orElseThrow();
    }
}