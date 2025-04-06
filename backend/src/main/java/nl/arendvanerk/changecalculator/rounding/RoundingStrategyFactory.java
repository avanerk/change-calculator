package nl.arendvanerk.changecalculator.rounding;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoundingStrategyFactory {

    private final Map<String, RoundingStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        strategyMap.put("EUR", new EuroRoundingStrategy());
    }

    public RoundingStrategy getStrategy(String currency) {
        return strategyMap.getOrDefault(currency, new NoRoundingStrategy());
    }
}

