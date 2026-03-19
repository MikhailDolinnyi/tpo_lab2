package ru.traphouse.stubs.trig;

import ru.traphouse.functions.trig.Cos;
import ru.traphouse.functions.trig.Cot;
import ru.traphouse.functions.trig.Sin;

import java.util.Map;

public class CotStub extends Cot {

    private static final Map<Double, Double> TABLE = Map.of(
            -Math.PI / 2,  0.0,
            -1.0,         -0.6421289641172216
    );

    public CotStub(Sin sin, Cos cos) {
        super(sin, cos);
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
