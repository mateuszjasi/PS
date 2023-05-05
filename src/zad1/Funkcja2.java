package zad1;

public class Funkcja2 implements IFunc {
    public double func(double x) {
        return (x * x - 2 * x + 1);
    }

    public double max(double a, double b) {
        double max = func(a);
        for (double i = a; i <= b; i++) {
            double val = func(i);
            if (val > max)
                max = val;
        }
        return max;
    }
}
