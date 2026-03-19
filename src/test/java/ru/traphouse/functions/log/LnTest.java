package ru.traphouse.functions.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LnTest {
    private static final double EPS = 1e-6;
    private Ln ln;

    @BeforeEach
    void setUp() {
        ln = new Ln(1e-10);
    }

    // ln(1) = 0, граничная точка области допустимых значений
    @Test
    void testOne() {
        assertEquals(0.0, ln.calculate(1.0), EPS);
    }

    // ln(e) = 1, основное тождество натурального логарифма
    @Test
    void testE() {
        assertEquals(1.0, ln.calculate(Math.E), EPS);
    }

    // ln(2), произвольное значение в области x > 1
    @Test
    void testTwo() {
        assertEquals(Math.log(2), ln.calculate(2.0), EPS);
    }

    // ln(0.5) < 0, проверяем корректность в области (0, 1)
    @Test
    void testFraction() {
        assertEquals(Math.log(0.5), ln.calculate(0.5), EPS);
    }

    // ln не определён при x = 0
    @Test
    void testUndefinedZero() {
        assertThrows(ArithmeticException.class, () -> ln.calculate(0.0));
    }

    // ln не определён для отрицательных аргументов
    @Test
    void testUndefinedNegative() {
        assertThrows(ArithmeticException.class, () -> ln.calculate(-1.0));
    }
}
