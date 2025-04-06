package nl.arendvanerk.changecalculator.rounding;

public class EuroRoundingStrategy implements RoundingStrategy {
    @Override
    public int round(int amountInCents) {
        int remainder = amountInCents % 5;
        if (remainder == 1 || remainder == 2) {
            return amountInCents - remainder;
        } else if (remainder == 3 || remainder == 4) {
            return amountInCents + (5 - remainder);
        } else {
            return amountInCents;
        }
    }
}
