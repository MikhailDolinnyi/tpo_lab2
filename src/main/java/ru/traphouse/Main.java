package ru.traphouse;

import java.io.IOException;

import ru.traphouse.functions.log.Ln;
import ru.traphouse.functions.log.Log10;
import ru.traphouse.functions.log.Log2;
import ru.traphouse.functions.log.Log3;
import ru.traphouse.functions.log.Log5;
import ru.traphouse.functions.trig.Cos;
import ru.traphouse.functions.trig.Cot;
import ru.traphouse.functions.trig.Csc;
import ru.traphouse.functions.trig.Sin;
import ru.traphouse.system.FunctionSystem;
import ru.traphouse.system.LogSystem;
import ru.traphouse.system.TrigSystem;
import ru.traphouse.util.CsvWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        double epsilon = 1e-10;

        // Тригонометрические модули
        Sin sin = new Sin(epsilon);
        Cos cos = new Cos(sin);
        Cot cot = new Cot(sin, cos);
        Csc csc = new Csc(sin);

        // Логарифмические модули
        Ln    ln    = new Ln(epsilon);
        Log2  log2  = new Log2(ln);
        Log3  log3  = new Log3(ln);
        Log5  log5  = new Log5(ln);
        Log10 log10 = new Log10(ln);

        // Системы
        TrigSystem     trigSystem = new TrigSystem(sin, cot, csc);
        LogSystem      logSystem  = new LogSystem(ln, log2, log3, log5, log10);
        FunctionSystem system     = new FunctionSystem(trigSystem, logSystem);

        // CSV-вывод
        CsvWriter.write("trig_output.csv", -3 * Math.PI, -0.1, 0.025,
                trigSystem::calculate, "TrigSystem(x)");

        CsvWriter.write("log_output.csv", 0.1, 10.0, 0.025,
                logSystem::calculate, "LogSystem(x)");

        CsvWriter.write("system_output.csv", -3 * Math.PI, 10.0, 0.025,
                system::calculate,            "F(x)");

        System.out.println("CSV files written: trig_output.csv, log_output.csv, system_output.csv");
    }
}
