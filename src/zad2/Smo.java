package zad2;
import dissimlab.random.RNGenerator;

public class Smo {
    public static void main(String[] args) {
        RNGenerator RNG = new RNGenerator(System.nanoTime());
        int N = 20, K_max = 250, K = 0, waiting = 0, handled = 0, rejected = 0, lambda = 4;
        double simTime = 0, Ts = 500.0, a = 5.0, b = 20.0;
        Linia[] line = new Linia[N];
        for(int i = 0; i < N; i++) {
            double Tz = RNG.uniform(a, b);
            line[i] = new Linia(Tz);
        }
        do {
            for(int i = 0; i < N; i++) {
                if(line[i].getTime() <= simTime && waiting > 0) {
                    line[i].FinishTime(simTime);
                    handled++;
                    waiting--;
                }
                else if(i == (N - 1) && waiting > 0) {
                    rejected++;
                    waiting--;
                }
            }
            K++;
            simTime += RNG.exponential(lambda);
            waiting++;
        } while(simTime < Ts && K <= K_max);
        K--;
        double estimated = (Ts / ((a + b) / 2) * N);
        double estimated_precent = (estimated / (Ts / (1 / (double)lambda))) * 100;
        if(estimated > K_max)
            estimated = (estimated_precent / 100) * K_max;
        System.out.println("Czas końcowy: " + simTime);
        System.out.println("Liczba zgłoszeń: " + K);
        System.out.println("Oszacowana liczba przyjętych zgloszeń: " + estimated + " (" + estimated_precent + "%)");
        System.out.println("Zgłoszenia przyjęte: " + handled + "(" + (handled / (double)K * 100.0) + "%) ");
        System.out.println("Zgłoszenia odrzucone: " + rejected + "(" + (rejected / (double)K * 100.0) + "%) ");
    }
}
