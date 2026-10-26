package main.java.ridehailing.assignment5.pricing;

public interface PricingStrategy {
    double calculateFare(double distanceKm, int estimatedMinutes);
}