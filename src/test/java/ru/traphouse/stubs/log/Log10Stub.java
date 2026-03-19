package ru.traphouse.stubs.log;

import ru.traphouse.functions.log.Ln;
import ru.traphouse.functions.log.Log10;

import java.util.Map;

public class Log10Stub extends Log10 {

    private static final Map<Double, Double> TABLE = Map.of(
            1.0,  0.0,
            0.5, -0.30102999566398114
    );

    public Log10Stub(Ln ln) {
        super(ln);
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
