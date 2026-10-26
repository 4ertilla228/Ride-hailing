package main.java.ridehailing.assignment5;

import main.java.ridehailing.assignment5.pricing.*;
import main.java.ridehailing.assignment5.matching.*;

public class StrategySelector {

    public PricingStrategy choosePricingStrategy(boolean isAirportTrip, boolean isRushHour, DemandSensor sensor, String zone) {
        if (isAirportTrip) {
            return new AirportFlatRateStrategy();
        }
        if (isRushHour) {
            return new SurgePricingStrategy(sensor, zone);
        }
        return new StandardPricingStrategy();
    }

    public DriverMatchingStrategy chooseMatchingStrategy(boolean isVipClient, boolean isUrgent) {
        if (isUrgent) {
            return new NearestDriverStrategy();
        }
        if (isVipClient) {
            return new HighestRatedDriverStrategy();
        }
        return new LongestIdleDriverStrategy();
    }
}