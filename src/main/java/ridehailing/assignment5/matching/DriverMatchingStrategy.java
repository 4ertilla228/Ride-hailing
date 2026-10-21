package main.java.ridehailing.assignment5.matching;
import java.util.Comparator;
import java.util.List;

public interface DriverMatchingStrategy {
    Driver matchDriver(List<Driver> availableDrivers);
}

// Стратегия 1
public class NearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .min(Comparator.comparingDouble(Driver::distanceToClientKm))
                .orElseThrow();
    }
}

// Стратегия 2
public class HighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .max(Comparator.comparingDouble(Driver::rating))
                .orElseThrow();
    }
}

// Стратегия 3
public class LongestIdleDriverStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(List<Driver> availableDrivers) {
        return availableDrivers.stream()
                .max(Comparator.comparingInt(Driver::idleTimeMinutes))
                .orElseThrow();
    }
}