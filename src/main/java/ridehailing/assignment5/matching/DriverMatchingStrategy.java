package main.java.ridehailing.assignment5.matching;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver matchDriver(List<Driver> availableDrivers);
}