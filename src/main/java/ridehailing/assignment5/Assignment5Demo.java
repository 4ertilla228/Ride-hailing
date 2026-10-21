package main.java.ridehailing.assignment5;

import main.java.ridehailing.assignment5.pricing.*;
import main.java.ridehailing.assignment5.matching.*;
import java.util.List;

public class Assignment5Demo {
    public static void main(String[] args) {
        DemandSensor fakeSensor = zone -> 2.5; // Симуляция внешних данных
        List<Driver> drivers = List.of(
                new Driver("D1", 5.0, 4.8, 10),
                new Driver("D2", 1.2, 4.5, 30),
                new Driver("D3", 8.0, 5.0, 5)
        );

        System.out.println("=== 1. SAME INPUT PROCESSED BY ALL STRATEGIES ===");
        PricingContext pContext = new PricingContext(new StandardPricingStrategy());
        System.out.println("Standard: " + pContext.executePricing(10, 20));
        pContext.setStrategy(new SurgePricingStrategy(fakeSensor, "Downtown"));
        System.out.println("Surge: " + pContext.executePricing(10, 20));
        pContext.setStrategy(new AirportFlatRateStrategy());
        System.out.println("Airport: " + pContext.executePricing(10, 20));

        System.out.println("\n=== 2. SELECTOR AUTOMATIC CHOICE ===");
        StrategySelector selector = new StrategySelector();

        DriverMatchingStrategy autoMatch = selector.chooseMatchingStrategy(true, false); // VIP, не срочно
        DriverMatchingContext mContext = new DriverMatchingContext(autoMatch);
        System.out.println("VIP Client assigned driver (Expected D3 - highest rating): " + mContext.executeMatching(drivers).id());

        System.out.println("\n=== 3. SWITCHING STRATEGY AT RUNTIME ===");
        System.out.println("Client changes mind, needs driver ASAP!");
        mContext.setStrategy(new NearestDriverStrategy()); // Ручная смена на лету
        System.out.println("Urgent Client assigned driver (Expected D2 - nearest): " + mContext.executeMatching(drivers).id());
    }
}