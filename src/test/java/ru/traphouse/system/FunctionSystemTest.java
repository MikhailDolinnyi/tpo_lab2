package ru.traphouse.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.traphouse.stubs.log.*;
import ru.traphouse.stubs.trig.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionSystemTest {
    private static final double EPS = 1e-4;

    private FunctionSystem system;

    @BeforeEach
    void setUp() {
        SinStub sin = new SinStub();
        CosStub cos = new CosStub(sin);
        TrigSystem trigSystem = new TrigSystem(sin, new CotStub(sin, cos), new CscStub(sin));

        LnStub ln = new LnStub();
        LogSystem logSystem = new LogSystem(ln, new Log2Stub(ln), new Log3Stub(ln), new Log5Stub(ln), new Log10Stub(ln));

        system = new FunctionSystem(trigSystem, logSystem);
    }

    // при x < 0 система использует тригонометрическую ветку
    @Test
    void testNegativeXUsesTrig() {
        double expected = Math.pow((Math.cos(-1.0) - 1.0) / Math.sin(-1.0), 6);
        assertEquals(expected, system.calculate(-1.0), EPS);
    }

    // при x > 0 система использует логарифмическую ветку
    @Test
    void testPositiveXUsesLog() {
        double x = 2.0;
        double lnVal    = Math.log(x);
        double log2Val  = Math.log(x) / Math.log(2);
        double log3Val  = Math.log(x) / Math.log(3);
        double log5Val  = Math.log(x) / Math.log(5);
        double log10Val = Math.log10(x);

        double num      = ((log10Val + lnVal + log10Val) - Math.pow(log3Val, 3)) * log10Val;
        double dTop     = log3Val + (log5Val - log2Val) * log2Val;
        double expected = num / (dTop / -lnVal);

        assertEquals(expected, system.calculate(x), EPS);
    }

    // x = 0 попадает в тригонометрическую ветку и бросает исключение
    @Test
    void testBoundaryZeroIsTrig() {
        assertThrows(ArithmeticException.class, () -> system.calculate(0.0));
    }

    // x = 1 попадает в логарифмическую ветку и бросает исключение (ln(1) = 0)
    @Test
    void testXEqualsOneIsUndefined() {
        assertThrows(ArithmeticException.class, () -> system.calculate(1.0));
    }
}
