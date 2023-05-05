package zad1;

public class Main {
    public static void main(String[] args) {
        IFunc funkcja1 = new Funkcja1();
        IFunc funkcja2 = new Funkcja2();
        double result, wanted_result = (double) 8 / 3;
        for(int n = 100; n <= 10000; n *= 10) {
            result = Calka.calculate(1, Math.exp(1), funkcja1, n);
            System.out.println("Całka 1 (" + n + " powtórzen): " + result);
            System.out.println("Błąd oszacowania: " + Math.abs(result - 3) / 3 * 100 + "%");
            System.out.println();
        }
        for(int n = 100; n <= 10000; n *= 10) {
            result = Calka.calculate(1, 3, funkcja2, n);
            System.out.println("Całka 2 (" + n + " powtórzen): " + result);
            System.out.println("Błąd oszacowania: " + Math.abs(result - wanted_result) / wanted_result * 100 + "%");
            System.out.println();
        }
    }
}