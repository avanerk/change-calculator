package nl.arendvanerk.changecalculator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyInfo {
    private String code;
    private String symbol;

    public CurrencyInfo(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
}
