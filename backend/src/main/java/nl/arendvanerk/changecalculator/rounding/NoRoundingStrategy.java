package nl.arendvanerk.changecalculator.rounding;

public class NoRoundingStrategy implements RoundingStrategy {
    @Override
    public int round(int amountInCents) {
        return amountInCents;
    }
}

