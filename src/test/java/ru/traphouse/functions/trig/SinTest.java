package ru.traphouse.functions.trig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinTest {
    private static final double EPS = 1e-6;
    private Sin sin;

    @BeforeEach
    void setUp() {
        sin = new Sin(1e-10);
    }

    // sin(0) = 0, граничная точка
    @Test
    void testZero() {
        assertEquals(0.0, sin.calculate(0.0), EPS);
    }

    // sin(pi/2) = 1, максимум функции в положительной области
    @Test
    void testPiHalf() {
        assertEquals(1.0, sin.calculate(Math.PI / 2), EPS);
    }

    // sin(pi) = 0, граница периода
    @Test
    void testPi() {
        assertEquals(0.0, sin.calculate(Math.PI), EPS);
    }

    // sin(-pi/2) = -1, минимум функции в отрицательной области
    @Test
    void testNegative() {
        assertEquals(-1.0, sin.calculate(-Math.PI / 2), EPS);
    }

    // sin(2pi) = 0, проверяем что нормализация через % 2pi работает корректно
    @Test
    void testTwoPi() {
        assertEquals(0.0, sin.calculate(2 * Math.PI), EPS);
    }

    // sin(-3pi/2): после % 2pi получаем -3pi/2 < -pi, срабатывает ветка x += 2pi, результат sin(pi/2) = 1
    @Test
    void testNormalizationNegativeBeyondMinusPi() {
        assertEquals(1.0, sin.calculate(-3 * Math.PI / 2), EPS);
    }

    // sin(3pi/2): после % 2pi получаем 3pi/2 > pi, срабатывает ветка x -= 2pi, результат sin(-pi/2) = -1
    @Test
    void testNormalizationPositiveBeyondPi() {
        assertEquals(-1.0, sin.calculate(3 * Math.PI / 2), EPS);
    }
}
