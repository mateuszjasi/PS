package Lab5;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.MonitoredVar;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        SimManager sm = SimManager.getInstance();
        // sm.setEndSimTime(400);
        // lamda - pojawianie sie interesantow, mi - czas obslugi, rho - cierpliwosc,
        // p - szansa na powrot do kolejki, alfa - czas naprawy, beta - czas dzialania
        double lambda = 0.4, mi = 0.5, rho = 0.1, p = 0.1, alfa = 0.2, beta = 0.05;
        int maxInteresantow = 200, maxDlugoscKolejki = 10, ileOkienek = 1;

        MonitoredVar czasPrzebywania = new MonitoredVar();
        MonitoredVar dlugoscKolejki = new MonitoredVar();
        MonitoredVar zajetosc = new MonitoredVar();
        MonitoredVar straty = new MonitoredVar();
        MonitoredVar stanZdatnosci = new MonitoredVar();

        ObiektAwaryjny systemKomputerowy = new ObiektAwaryjny(alfa, beta, stanZdatnosci);
        Poczta poczta = new Poczta(mi, czasPrzebywania, dlugoscKolejki, zajetosc, straty, systemKomputerowy, maxDlugoscKolejki, ileOkienek, p, rho);
        systemKomputerowy.setPoczta(poczta);

        Otoczenie otoczenie = new Otoczenie(poczta, lambda, maxInteresantow);
        new ZdarzenieNowyInteresant(otoczenie, 0);
        new ZdarzenieAwaria(systemKomputerowy, systemKomputerowy.generujCzasZdatnosci());
        sm.startSimulation();
        Diagram diagram1 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas przebywania interesantów");
        diagram1.add(czasPrzebywania, Color.BLUE);
        diagram1.show();

        Diagram diagram2 = new Diagram(Diagram.DiagramType.TIME, "Liczba osób w kolejce");
        diagram2.add(dlugoscKolejki, Color.RED);
        diagram2.show();

        Diagram diagram3 = new Diagram(Diagram.DiagramType.TIME, "Zajętość okienek");
        diagram3.add(zajetosc, Color.GREEN);
        diagram3.show();

        System.out.println("Średni czas przebywania: " + Statistics.weightedMean(czasPrzebywania));
        System.out.println("Średnia długość kolejki: " + Statistics.weightedMean(dlugoscKolejki));
        System.out.println("Średnia zajętość okienek: " + Statistics.weightedMean(zajetosc));
        System.out.println("Prawdopodobieństwo straty interesanta: " +  straty.getValue()/otoczenie.getLicznikInteresantow() * 100 + "%");
    }
}
