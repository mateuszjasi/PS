package Lab3;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

public class Main {
    public static void main(String[] args) throws SimControlException, InterruptedException {
        double a = 4, b = 8 , c = 4, d = 8;
        RNGenerator rng = new RNGenerator();
        SimManager sm = SimManager.getInstance();
        sm.setEndSimTime(1000.0);
        Szlak sz = new Szlak(1000 , c ,d, 10);
        Ruch ruch = new Ruch(sz,null,1.0);
        Tworzenie gen = new Tworzenie(sz,null,1.0, a, b, rng.uniform(a, b));
        sm.startSimulation();
        Diagram d1 = new Diagram(Diagram.DiagramType.TIME, "Liczba pieszych");
        Diagram d2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas przemarszu");
        d1.add(sz.liczbaPieszychNaSzlaku, java.awt.Color.BLUE);
        d2.add(sz.czasPrzemarszu, java.awt.Color.RED);
        d1.show();
        d2.show();
        System.out.println("Średnia liczba osób na trasie: " + (int)Statistics.weightedMean(sz.liczbaPieszychNaSzlaku));
        System.out.println("Średni czas przemarszu: " + Statistics.arithmeticMean(sz.czasPrzemarszu));
    }
}
