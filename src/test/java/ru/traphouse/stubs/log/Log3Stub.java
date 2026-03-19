package ru.traphouse.stubs.log;

import ru.traphouse.functions.log.Ln;
import ru.traphouse.functions.log.Log3;

import java.util.Map;

public class Log3Stub extends Log3 {

    private static final Map<Double, Double> TABLE = Map.of(
            1.0,  0.0,
            0.5, -0.6309297535714573
    );

    public Log3Stub(Ln ln) {
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
