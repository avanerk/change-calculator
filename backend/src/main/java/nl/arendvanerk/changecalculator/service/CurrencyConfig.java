package nl.arendvanerk.changecalculator.service;

import lombok.Getter;

@Getter
public class CurrencyConfig {
    private final String code;
    private final String symbol;
    private final int[] denominations;

    public CurrencyConfig(String code, String symbol, int[] denominations) {
        this.code = code;
        this.symbol = symbol;
        this.denominations = denominations;
    }
}