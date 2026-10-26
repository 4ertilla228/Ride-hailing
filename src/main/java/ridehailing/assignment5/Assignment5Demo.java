package main.java.ridehailing.assignment5;

import main.java.ridehailing.assignment5.pricing.*;
import main.java.ridehailing.assignment5.matching.*;
import java.util.List;

public class Assignment5Demo {
    public static void main(String[] args) {
        DemandSensor fakeSensor = zone -> 2.5;
        List<Driver> drivers = List.of(
                new Driver("D1", 5.0, 4.8, 10),
                new Driver("D2", 1.2, 4.5, 30),
                new Driver("D3", 8.0, 5.0, 5)
        );

        System.out.println("=== 1. SAME INPUT PROCESSED BY ALL STRATEGIES ===");
        PricingContext pContext = new PricingContext(new StandardPricingStrategy());
        System.out.println("Standard Pricing (10km, 20min): " + pContext.executePricing(10, 20));

        pContext.setStrategy(new SurgePricingStrategy(fakeSensor, "Downtown"));
        System.out.println("Surge Pricing (10km, 20min): " + pContext.executePricing(10, 20));

        pContext.setStrategy(new AirportFlatRateStrategy());
        System.out.println("Airport Pricing (10km, 20min): " + pContext.executePricing(10, 20));

        System.out.println("\n=== 2. MANUAL CHOICE VS AUTOMATIC SELECTOR CHOICE ===");
        StrategySelector selector = new StrategySelector();

        DriverMatchingContext manualContext = new DriverMatchingContext(new LongestIdleDriverStrategy());
        System.out.println("Manual choice (Longest Idle): " + manualContext.executeMatching(drivers).id());

        DriverMatchingStrategy autoMatch = selector.chooseMatchingStrategy(true, false); // VIP, не срочно
        DriverMatchingContext mContext = new DriverMatchingContext(autoMatch);
        System.out.println("Selector automatic choice for VIP (Highest Rated): " + mContext.executeMatching(drivers).id());

        System.out.println("\n=== 3. SWITCHING STRATEGY AT RUNTIME ===");
        System.out.println("Client changes mind at runtime, needs driver ASAP!");
        mContext.setStrategy(new NearestDriverStrategy());
        System.out.println("Urgent Client assigned driver (Nearest): " + mContext.executeMatching(drivers).id());
    }
}