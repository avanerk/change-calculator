package nl.arendvanerk.changecalculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeCalculatorTest {

    private ChangeCalculator changeCalculator;

    @BeforeEach
    void setup() {
        CurrencyConfigFactory factory = new CurrencyConfigFactory();
        factory.init();
        changeCalculator = new ChangeCalculator(factory);
    }

    @Test
    void shouldReturnCorrectChangeWhenCustomerOverPays_EUR() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(4575, 5000, "EUR");
        assertEquals(2, result.get(200));
        assertEquals(1, result.get(25));
    }

    @Test
    void shouldReturnCorrectChangeWhenCustomerOverPays_USD() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(9875, 10000, "USD");
        assertEquals(1, result.get(100));
        assertEquals(1, result.get(25));
    }

    @Test
    void shouldReturnEmptyMapWhenNoChangeIsNeeded() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(1000, 1000, "EUR");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyMapWhenNoChangeIsNeeded_USD() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(500, 500, "USD");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenPaidAmountIsLessThanPurchaseAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            changeCalculator.calculateChange(1000, 900, "EUR");
        });
    }

    @Test
    void shouldUseDefaultCurrencyWhenUnknownProvided() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(4575, 5000, "XYZ");
        assertEquals(2, result.get(200));
        assertEquals(1, result.get(25));
    }
}
