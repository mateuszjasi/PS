package zad1;
public class MyRandom {
    private int x;
    private final int a, b, M;

    public MyRandom(int seed, int a, int b, int M) {
        x = seed;
        this.a = a;
        this.b = b;
        this.M = M;
    }

    public int nextInt() {
        return x = (a * x + b) % M;
    }

    public double nextDouble() {
        x = nextInt();
        return (double)x / (double)M;
    }

    public double nextDouble(double low, double high) {
        return low + (high - low) * nextDouble();
    }

    public double exponential(double lambda) {
        return (-1) * (Math.log(1 - nextDouble() ) / lambda);
    }

    public double dyskret(double[] xx, double[] p) {
        double sum = 0, k = 0;
        for(int i = 0; i < xx.length; i++) {
            sum += p[i];
            if(sum > nextDouble())
                return xx[i];
        }
        return k;
    }
}