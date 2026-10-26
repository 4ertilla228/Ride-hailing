package main.java.ridehailing.assignment5.matching;

import java.util.List;

public class DriverMatchingContext {
    private DriverMatchingStrategy strategy;

    public DriverMatchingContext(DriverMatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(DriverMatchingStrategy strategy) {
        this.strategy = strategy;
    }

    public Driver executeMatching(List<Driver> drivers) {
        return strategy.matchDriver(drivers);
    }
}