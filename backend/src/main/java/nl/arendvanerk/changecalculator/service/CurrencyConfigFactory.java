package nl.arendvanerk.changecalculator.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CurrencyConfigFactory {

    private final Map<String, CurrencyConfig> currencyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        currencyMap.put("EUR", new CurrencyConfig("EUR", "€", new int[]{
                5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5
        }));
        currencyMap.put("USD", new CurrencyConfig("USD", "$", new int[]{
                10000, 5000, 2000, 1000, 500, 100, 25, 10, 5, 1
        }));
        currencyMap.put("GBP", new CurrencyConfig("GBP", "£", new int[]{
                5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1
        }));
        currencyMap.put("CAD", new CurrencyConfig("CAD", "C$", new int[]{
                10000, 5000, 2000, 1000, 500, 200, 100, 25, 10, 5
        }));
        currencyMap.put("AUD", new CurrencyConfig("AUD", "A$", new int[]{
                10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5
        }));
        currencyMap.put("CHF", new CurrencyConfig("CHF", "Fr.", new int[]{
                10000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5
        }));
    }

    public CurrencyConfig getConfig(String currency) {
        return currencyMap.getOrDefault(currency, currencyMap.get("EUR"));
    }

    public Collection<CurrencyConfig> getAllConfigs() {
        return currencyMap.values();
    }

    public Set<String> getSupportedCurrencies() {
        return currencyMap.keySet();
    }
}

