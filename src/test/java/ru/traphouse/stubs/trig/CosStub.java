package ru.traphouse.stubs.trig;

import ru.traphouse.functions.trig.Cos;
import ru.traphouse.functions.trig.Sin;

import java.util.Map;

public class CosStub extends Cos {

    private static final Map<Double, Double> TABLE = Map.of(
            0.0,          1.0,
            Math.PI / 2,  0.0,
            Math.PI,     -1.0,
            -Math.PI / 2, 0.0,
            -1.0,         0.5403023058681398
    );

    public CosStub(Sin sin) {
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
