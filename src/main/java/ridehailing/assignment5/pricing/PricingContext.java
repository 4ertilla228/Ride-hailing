package main.java.ridehailing.assignment5.pricing;

public class PricingContext {
    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double executePricing(double distanceKm, int estimatedMinutes) {
        return strategy.calculateFare(distanceKm, estimatedMinutes);
    }
}