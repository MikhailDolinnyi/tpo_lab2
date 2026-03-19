package ru.traphouse.functions.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    private static final double EPS = 1e-6;
    private Log2 log2;
    private Log3 log3;
    private Log5 log5;
    private Log10 log10;

    @BeforeEach
    void setUp() {
        Ln ln = new Ln(1e-10);
        log2  = new Log2(ln);
        log3  = new Log3(ln);
        log5  = new Log5(ln);
        log10 = new Log10(ln);
    }

    // log2(2) = 1, логарифм числа равного основанию равен 1
    @Test
    void testLog2AtTwo() {
        assertEquals(1.0, log2.calculate(2.0), EPS);
    }

    // log3(3) = 1
    @Test
    void testLog3AtThree() {
        assertEquals(1.0, log3.calculate(3.0), EPS);
    }

    // log5(5) = 1
    @Test
    void testLog5AtFive() {
        assertEquals(1.0, log5.calculate(5.0), EPS);
    }

    // log10(10) = 1
    @Test
    void testLog10AtTen() {
        assertEquals(1.0, log10.calculate(10.0), EPS);
    }

    // логарифм 1 по любому основанию равен 0, граничная точка
    @Test
    void testLogAtOne() {
        assertEquals(0.0, log2.calculate(1.0), EPS);
        assertEquals(0.0, log3.calculate(1.0), EPS);
        assertEquals(0.0, log5.calculate(1.0), EPS);
        assertEquals(0.0, log10.calculate(1.0), EPS);
    }

    // log10(7), нецелое значение аргумента; log2/log3/log5 реализованы аналогично через ln(x)/ln(base)
    @Test
    void testLog10NonInteger() {
        assertEquals(Math.log10(7.0), log10.calculate(7.0), EPS);
    }
}
