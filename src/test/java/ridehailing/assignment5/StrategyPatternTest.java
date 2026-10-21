package test.java.ridehailing.assignment5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import test.java.ridehailing.assignment5.pricing.*;
import test.java.ridehailing.assignment5.matching.*;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StrategyPatternTest {

    // Фейк внешнего источника данных
    private final DemandSensor fakeSensor = zone -> 3.0;

    private final List<Driver> testDrivers = List.of(
            new Driver("D1", 1.0, 4.0, 5),
            new Driver("D2", 5.0, 5.0, 2),
            new Driver("D3", 3.0, 4.5, 60)
    );

    @Test
    void standardPricingCalculatesCorrectly() {
        PricingStrategy strategy = new StandardPricingStrategy();
        assertEquals(1900.0, strategy.calculateFare(10.0, 20)); // 1500 + 400
    }

    @Test
    void surgePricingUsesFakeExternalData() {
        PricingStrategy strategy = new SurgePricingStrategy(fakeSensor, "ZoneA");
        assertEquals(5700.0, strategy.calculateFare(10.0, 20)); // (1500 + 400) * 3.0
    }

    @Test
    void selectorChoosesCorrectMatchingStrategy() {
        StrategySelector selector = new StrategySelector();
        assertTrue(selector.chooseMatchingStrategy(false, true) instanceof NearestDriverStrategy);
        assertTrue(selector.chooseMatchingStrategy(true, false) instanceof HighestRatedDriverStrategy);
    }

    @Test
    void longestIdleDriverIsSelected() {
        DriverMatchingStrategy strategy = new LongestIdleDriverStrategy();
        assertEquals("D3", strategy.matchDriver(testDrivers).id());
    }

    // Параметризованный тест для всей семьи стратегий
    static Stream<PricingStrategy> providePricingStrategies() {
        return Stream.of(
                new StandardPricingStrategy(),
                new SurgePricingStrategy(z -> 1.5, "ZoneA"),
                new AirportFlatRateStrategy()
        );
    }

    @ParameterizedTest
    @MethodSource("providePricingStrategies")
    void fareIsNeverNegativeForAllStrategies(PricingStrategy strategy) {
        PricingContext context = new PricingContext(strategy);
        assertTrue(context.executePricing(5.0, 10) > 0);
    }
}