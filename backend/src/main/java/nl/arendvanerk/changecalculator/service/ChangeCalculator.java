package nl.arendvanerk.changecalculator.service;

import nl.arendvanerk.changecalculator.model.CurrencyInfo;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChangeCalculator {

    private final CurrencyConfigFactory factory;

    public ChangeCalculator(CurrencyConfigFactory factory) {
        this.factory = factory;
    }

    public Map<Integer, Integer> calculateChange(int amount, int paid, String currency) {
        if (paid < amount) {
            throw new IllegalArgumentException("Betaald bedrag moet groter of gelijk zijn aan het aankoopbedrag.");
        }

        CurrencyConfig config = factory.getConfig(currency);
        int[] denominations = config.getDenominations();
        int change = paid - amount;

        Map<Integer, Integer> result = new LinkedHashMap<>();

        for (int denomination : denominations) {
            int times = change / denomination;
            if (times > 0) {
                change -= denomination * times;
                result.put(denomination, times);
            }
        }

        return result;
    }

    public List<CurrencyInfo> getSupportedCurrencyInfo() {
        return factory.getAllConfigs().stream()
                .map(cfg -> new CurrencyInfo(cfg.getCode(), cfg.getSymbol()))
                .collect(Collectors.toList());
    }
}
