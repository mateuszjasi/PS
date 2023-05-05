package zad1;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //zad1
        int a = 13, b = 37, M = 100, seed = 69;
        double low = 0d, high = 20d;
        MyRandom generator = new MyRandom(seed, a, b, M);
        Random generator2 = new Random();
        int sum1 = 0, sum2 = 0;
        double sumD = 0d, sumD2 = 0d, sumE = 0d;
        for(int i = 0; i < 1000; i++) {
            sum1 += generator.nextInt();
            sum2 += generator2.nextInt(M);
        }
        System.out.println("Zadanie 1:");
        System.out.println("\nnextInt()");
        System.out.println("Wartość oczekiwana: " + M/2);
        System.out.println("Średnia generatora własnego: " + (double) sum1 / 1000);
        System.out.println("Średnia generatora wbudowanego: " + (double) sum2 / 1000);

        for(int i = 0; i < 1000; i++)
            sumD += generator.nextDouble();
        System.out.println("\nDnextDouble()");
        System.out.println("Wartość oczekiwana: " + 0.5);
        System.out.println("Średnia generatora własnego: " + sumD / 1000);

        for(int i = 0; i < 1000; i++)
            sumD2 += generator.nextDouble(low, high);
        System.out.println("\nnextDouble(" + low + ", " + high + ")");
        System.out.println("Wartość oczekiwana: " + (low + (high - low) / 2));
        System.out.println("Średnia generatora własnego: " + sumD2 / 1000);

        double lambda = 100d;
        for(int i = 0; i < 1000; i++)
            sumE += generator.exponential(lambda);
        System.out.println("\nexponential()");
        System.out.println("Wartość oczekiwana: " + 1 / lambda);
        System.out.println("Średnia generatora własnego: " + sumE / 1000);

        //zad2
        double[] p = {0.1, 0.15, 0.2, 0.1, 0.25, 0.15, 0.05};
        double[] xx = {-2d, -1d, 0d, 1d, 2d, 3d, 4d};
        double sumDys = 0;
        double check_p = 0;
        for(int i = 0; i < p.length; i++)
            check_p += p[i];
        System.out.println("\nZadanie 2:\n");
        if(check_p != 1 || p.length != xx.length) {
            System.out.println("Błąd danych!");
        } else {
            for(int i = 0; i < 1000; i++)
                sumDys += generator.dyskret(xx, p);
            double sredniaD = 0;
            for(int i = 0; i<xx.length; i++)
                sredniaD+= xx[i]*p[i];
            System.out.println("dyskret()");
            System.out.println("Wartość oczekiwana: " + sredniaD);
            System.out.println("Średnia generatora własnego: " + sumDys / 1000);
        }
    }
}