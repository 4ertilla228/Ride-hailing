package main.java.ridehailing.assignment5.pricing;

public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculateFare(double distanceKm, int estimatedMinutes) {
        return (distanceKm * 150.0) + (estimatedMinutes * 20.0);
    }
}