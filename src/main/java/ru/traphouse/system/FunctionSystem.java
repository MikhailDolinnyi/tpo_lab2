package ru.traphouse.system;

/**
 * Система функций:
 * x <= 0 => TrigSystem
 * x > 0  => LogSystem
 */
public record FunctionSystem(TrigSystem trigSystem, LogSystem logSystem) {

    public double calculate(double x) {
        if (x <= 0) {
            return trigSystem.calculate(x);
        } else {
            return logSystem.calculate(x);
        }
    }
}
