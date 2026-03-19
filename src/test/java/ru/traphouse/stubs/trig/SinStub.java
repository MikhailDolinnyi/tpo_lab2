package ru.traphouse.stubs.trig;

import ru.traphouse.functions.trig.Sin;

import java.util.Map;

public class SinStub extends Sin {

    private static final Map<Double, Double> TABLE = Map.of(
            0.0,           0.0,
            Math.PI / 2,   1.0,
            Math.PI,       0.0,
            -Math.PI / 2, -1.0,
            -1.0,         -0.8414709848078965
    );

    public SinStub() {
        super(1e-10);
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
