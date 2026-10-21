package main.java.ridehailing.assignment5;

public class BadPricingCalculator {
    public double calculateFare(String type, double distance, double surgeMultiplier) {
        switch (type) {
            case "STANDARD":
                return distance * 150.0;
            case "SURGE":
                return (distance * 150.0) * surgeMultiplier;
            case "AIRPORT":
                return 5000.0;
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }
}