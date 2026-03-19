package ru.traphouse.stubs.trig;

import ru.traphouse.functions.trig.Csc;
import ru.traphouse.functions.trig.Sin;

import java.util.Map;

public class CscStub extends Csc {

    private static final Map<Double, Double> TABLE = Map.of(
            -Math.PI / 2, -1.0,
            -1.0,         -1.1883951057561573
    );

    public CscStub(Sin sin) {
        super(sin);
    }

    @Override
    public double calculate(double x) {
        for (Map.Entry<Double, Double> entry : TABLE.entrySet()) {
            if (Math.abs(entry.getKey() - x) < 1e-9) {
                return entry.getValue();
            }
        }
        return super.calculate(x);
    }
}
