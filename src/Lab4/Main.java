package Lab4;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.MonitoredVar;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        SimManager sm = SimManager.getInstance();
        double lambda = 1, mi = 0.95;
        int maxInteresantow = 100;

        MonitoredVar czasPrzebywania = new MonitoredVar();
        MonitoredVar dlugoscKolejki = new MonitoredVar();
        MonitoredVar zajetosc = new MonitoredVar();

        Poczta poczta = new Poczta(new RNGenerator(RNGenerator.generateSeed()), mi, czasPrzebywania, dlugoscKolejki, zajetosc);
        Otoczenie otoczenie = new Otoczenie(new RNGenerator(RNGenerator.generateSeed()), poczta, lambda, maxInteresantow);
        ZdarzenieNowyInteresant start = new ZdarzenieNowyInteresant(otoczenie, 0);
        sm.startSimulation();

        Diagram diagram1 = new Diagram(Diagram.DiagramType.TIME, "Zajętość kolejki w czasie");
        diagram1.add(dlugoscKolejki, Color.GREEN);
        diagram1.show();

        Diagram diagram2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas przebywania interesanta");
        diagram2.add(czasPrzebywania, Color.BLUE);
        diagram2.show();

        System.out.println("Średni czas przebywania: " + Statistics.arithmeticMean(czasPrzebywania));
        System.out.println("Średnia długość kolejki: " + Statistics.arithmeticMean(dlugoscKolejki));
        System.out.println("Średnia zajętość okienka: " + Statistics.arithmeticMean(zajetosc));
    }
}
