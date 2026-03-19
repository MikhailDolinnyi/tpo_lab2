package ru.traphouse.functions.trig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosTest {
    private static final double EPS = 1e-6;
    private Cos cos;

    @BeforeEach
    void setUp() {
        Sin sin = new Sin(1e-10);
        cos = new Cos(sin);
    }

    // cos(0) = 1, максимум функции
    @Test
    void testZero() {
        assertEquals(1.0, cos.calculate(0.0), EPS);
    }

    // cos(pi/2) = 0, пересечение нуля
    @Test
    void testPiHalf() {
        assertEquals(0.0, cos.calculate(Math.PI / 2), EPS);
    }

    // cos(pi) = -1, минимум функции
    @Test
    void testPi() {
        assertEquals(-1.0, cos.calculate(Math.PI), EPS);
    }

    // cos(pi/3) = 0.5, произвольное значение в интервале (0, pi/2)
    @Test
    void testPiThird() {
        assertEquals(0.5, cos.calculate(Math.PI / 3), EPS);
    }

    // cos(-1), проверяем корректность в отрицательной области
    @Test
    void testNegativeValue() {
        assertEquals(Math.cos(-1.0), cos.calculate(-1.0), EPS);
    }
}
