package ru.traphouse.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.traphouse.stubs.trig.CosStub;
import ru.traphouse.stubs.trig.CotStub;
import ru.traphouse.stubs.trig.CscStub;
import ru.traphouse.stubs.trig.SinStub;

import static org.junit.jupiter.api.Assertions.*;

class TrigSystemTest {
    private static final double EPS = 1e-4;

    private TrigSystem trigSystem;

    @BeforeEach
    void setUp() {
        SinStub sin = new SinStub();
        CosStub cos = new CosStub(sin);
        trigSystem = new TrigSystem(sin, new CotStub(sin, cos), new CscStub(sin));
    }

    // система не определена при x = 0, так как sin(0) = 0
    @Test
    void testUndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> trigSystem.calculate(0.0));
    }

    // система не определена при x = -π, так как sin(-π) = 0
    @Test
    void testUndefinedAtMinusPi() {
        assertThrows(ArithmeticException.class, () -> trigSystem.calculate(-Math.PI));
    }

    // проверяем корректность вычисления для произвольного x = -1
    @Test
    void testAtMinusOne() {
        double expected = Math.pow((Math.cos(-1.0) - 1.0) / Math.sin(-1.0), 6);
        assertEquals(expected, trigSystem.calculate(-1.0), EPS);
    }

    // при x = -π/2 внутреннее выражение равно 1, результат 1^6 = 1
    @Test
    void testAtMinusPiHalf() {
        assertEquals(1.0, trigSystem.calculate(-Math.PI / 2), EPS);
    }
}
