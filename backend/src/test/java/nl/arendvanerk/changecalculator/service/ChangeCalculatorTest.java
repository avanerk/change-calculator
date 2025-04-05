package nl.arendvanerk.changecalculator.service;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeCalculatorTest {

    private final ChangeCalculator changeCalculator = new ChangeCalculator();

    @Test
    void shouldReturnCorrectChangeWhenCustomerOverPays() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(4575, 50000);
        assertEquals(2, result.get(200));
        assertEquals(1, result.get(25));
    }

    @Test
    void shouldReturnEmptyMapWhenNoChangeIsNeeded() {
        Map<Integer, Integer> result = changeCalculator.calculateChange(1000, 1000);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenPaidAmountIsLessThanPurchaseAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            changeCalculator.calculateChange(1000, 900);
        });
    }

}
