package main.java.ridehailing.assignment5.pricing;

import main.java.ridehailing.assignment5.DemandSensor;

public class SurgePricingStrategy implements PricingStrategy {
    private final DemandSensor demandSensor;
    private final String zone;

    public SurgePricingStrategy(DemandSensor demandSensor, String zone) {
        this.demandSensor = demandSensor;
        this.zone = zone;
    }

    @Override
    public double calculateFare(double distanceKm, int estimatedMinutes) {
        double base = (distanceKm * 150.0) + (estimatedMinutes * 20.0);
        return base * demandSensor.getCurrentSurgeMultiplier(zone);
    }
}