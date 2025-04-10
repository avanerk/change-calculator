package nl.arendvanerk.changecalculator.controller;

import nl.arendvanerk.changecalculator.model.ChangeRequest;
import nl.arendvanerk.changecalculator.model.CurrencyInfo;
import nl.arendvanerk.changecalculator.service.ChangeCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ChangeController {
    private final ChangeCalculator changeCalculator;

    public ChangeController(ChangeCalculator changeCalculator) {
        this.changeCalculator = changeCalculator;
    }

    @PostMapping("/change")
    public ResponseEntity<Map<Integer, Integer>> calculateChange(@RequestBody ChangeRequest request) {
        Map<Integer, Integer> result = changeCalculator.calculateChange(request.getAmount(), request.getPaid(), request.getCurrency());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/currencies/info")
    public ResponseEntity<List<CurrencyInfo>> getCurrencyInfo() {
        return ResponseEntity.ok(changeCalculator.getSupportedCurrencyInfo());
    }

}
