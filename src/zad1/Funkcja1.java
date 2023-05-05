package zad1;

public class Funkcja1 implements IFunc {
    public double func(double x) {
        if(x != 0)
            return 3 / x;
        else
            return 3;
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
