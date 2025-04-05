package nl.arendvanerk.changecalculator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRequest {
    private int amount;
    private int paid;
}
