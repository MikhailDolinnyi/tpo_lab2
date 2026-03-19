package ru.traphouse.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.traphouse.stubs.log.*;

import static org.junit.jupiter.api.Assertions.*;

class LogSystemTest {
    private static final double EPS = 1e-4;

    private LogSystem logSystem;

    @BeforeEach
    void setUp() {
        LnStub ln = new LnStub();
        logSystem = new LogSystem(ln, new Log2Stub(ln), new Log3Stub(ln), new Log5Stub(ln), new Log10Stub(ln));
    }

    // при x = 1 знаменатель обнуляется (ln(1) = 0), система не определена
    @Test
    void testUndefinedAtOne() {
        assertThrows(ArithmeticException.class, () -> logSystem.calculate(1.0));
    }

    // проверяем корректность вычисления для x = 2 (область x > 1)
    @Test
    void testAtTwo() {
        double x = 2.0;
        double lnVal    = Math.log(x);
        double log2Val  = Math.log(x) / Math.log(2);
        double log3Val  = Math.log(x) / Math.log(3);
        double log5Val  = Math.log(x) / Math.log(5);
        double log10Val = Math.log10(x);

        double num      = ((log10Val + lnVal + log10Val) - Math.pow(log3Val, 3)) * log10Val;
        double dTop     = log3Val + (log5Val - log2Val) * log2Val;
        double expected = num / (dTop / -lnVal);

        assertEquals(expected, logSystem.calculate(x), EPS);
    }

    // проверяем корректность вычисления для x = 0.5 (область (0, 1), где все логарифмы отрицательны)
    @Test
    void testAtPointFive() {
        double x = 0.5;
        double lnVal    = Math.log(x);
        double log2Val  = Math.log(x) / Math.log(2);
        double log3Val  = Math.log(x) / Math.log(3);
        double log5Val  = Math.log(x) / Math.log(5);
        double log10Val = Math.log10(x);

        double num      = ((log10Val + lnVal + log10Val) - Math.pow(log3Val, 3)) * log10Val;
        double dTop     = log3Val + (log5Val - log2Val) * log2Val;
        double expected = num / (dTop / -lnVal);

        assertEquals(expected, logSystem.calculate(x), EPS);
    }
}
