package ru.traphouse.functions.trig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CotTest {
    private static final double EPS = 1e-6;
    private Cot cot;

    @BeforeEach
    void setUp() {
        Sin sin = new Sin(1e-10);
        Cos cos = new Cos(sin);
        cot = new Cot(sin, cos);
    }

    // cot(pi/4) = 1, проверяем точное значение в положительной области
    @Test
    void testPiQuarter() {
        assertEquals(1.0, cot.calculate(Math.PI / 4), EPS);
    }

    // cot(-1), проверяем произвольное значение в отрицательной области
    @Test
    void testNegativeValue() {
        assertEquals(Math.cos(-1.0) / Math.sin(-1.0), cot.calculate(-1.0), EPS);
    }

    // cot не определён при x = 0, так как sin(0) = 0
    @Test
    void testUndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> cot.calculate(0.0));
    }

    // cot не определён при x = pi, так как sin(pi) = 0
    @Test
    void testUndefinedAtPi() {
        assertThrows(ArithmeticException.class, () -> cot.calculate(Math.PI));
    }
}
