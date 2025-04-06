package nl.arendvanerk.changecalculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChangeCalculatorTest {

    @Autowired
    private ChangeCalculator changeCalculator;

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

    @Test
    void shouldRound994To995AndReturn5CentsChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(994, 1000, "EUR");
        assertEquals(1, result.get(5));
    }

    @Test
    void shouldRound992To990AndReturn10CentsChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(992, 1000, "EUR");
        assertEquals(1, result.get(10));
    }

    @Test
    void shouldRound993To995AndReturn5CentsChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(993, 1000, "EUR");
        assertEquals(1, result.get(5));
    }

    @Test
    void shouldRound997To995AndReturn5CentsChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(997, 1000, "EUR");
        assertEquals(1, result.get(5));
    }

    @Test
    void shouldRound998To1000AndReturnNoChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(998, 1000, "EUR");
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldRound1002To1000AndReturnNoChange() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(1002, 1000, "EUR");
        assertTrue(result.isEmpty());
    }
}
