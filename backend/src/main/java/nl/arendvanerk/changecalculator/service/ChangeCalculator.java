package nl.arendvanerk.changecalculator.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChangeCalculator {

    private final int[] denominations = {
            5000, 2000, 1000, 500, 200, 100, 50, 25, 20, 10, 5
    };

    public Map<Integer, Integer> calculateChange(int amount, int paid) {
        int change = paid - amount;

        if (change < 0) {
            throw new IllegalArgumentException("The paid amount should be greater than the product amount");
        }

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

}
