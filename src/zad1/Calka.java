package zad1;
import dissimlab.random.RNGenerator;

public class Calka {
    public static double calculate(double a, double b, IFunc f ,int rep) {
        RNGenerator randomGen = new RNGenerator(System.nanoTime());
        double x, y, fmax = f.max(a, b);
        int k = 0;
        for(int i = 0; i < rep; i++) {
            y = randomGen.uniform(0, fmax);
            x = randomGen.uniform(a, b);
            if(y <=  f.func(x))
                k++;
        }
        return ((double)k / rep) * (b - a) * fmax;
    }
}
