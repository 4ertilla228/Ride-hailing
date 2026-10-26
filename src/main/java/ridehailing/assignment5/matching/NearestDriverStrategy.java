package main.java.ridehailing.assignment5.matching;

import java.util.Comparator;
import java.util.List;

public class NearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .min(Comparator.comparingDouble(Driver::distanceToClientKm))
                .orElseThrow();
    }
}