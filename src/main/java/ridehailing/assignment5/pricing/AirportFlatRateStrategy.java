package main.java.ridehailing.assignment5.pricing;

public class AirportFlatRateStrategy implements PricingStrategy {
    @Override
    public double calculateFare(double distanceKm, int estimatedMinutes) {
        return 5000.0;
    }
}