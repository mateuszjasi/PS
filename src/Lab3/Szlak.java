package Lab3;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Szlak extends BasicSimObj {
    ArrayList<Pieszy> tab = new ArrayList<Pieszy>();
    private double liczbaOdcinkow;
    double liczba, dlugosc, c = 5, d = 20, w = 0.0;
    RNGenerator rng = new RNGenerator();
    int i = 0, q = 0;
    MonitoredVar liczbaPieszychNaSzlaku = new MonitoredVar(0);
    MonitoredVar czasPrzemarszu = new MonitoredVar(0);

    public Szlak(double dlugosc, double c, double d, double liczbaOdcinkow) {
        this.dlugosc = dlugosc;
        this.c = c;
        this.d = d;
        this.liczbaOdcinkow = liczbaOdcinkow;
    }

    public void nowy_pieszy(double czas){
        Pieszy pieszy = new Pieszy(i, rng.uniform(c,d), dlugosc ,liczbaOdcinkow );
        tab.add(pieszy) ;
        liczba = liczbaPieszychNaSzlaku.getValue();
        liczbaPieszychNaSzlaku.setValue(liczba + 1.0  ,czas);
        System.out.println(simTime() + " Przybycie, Pieszy " + i );
        i++;
    }
    public void opis() {
        for (q = 0; q < tab.size(); q++) {
            w = tab.get(q).przebytaDroga(dlugosc);
            if (w != -1.0) {
                tab.remove(q);
                q--;
                liczba =  liczbaPieszychNaSzlaku.getValue();
                liczbaPieszychNaSzlaku.setValue(liczba - 1.0 , simTime());
                czasPrzemarszu.setValue(w , simTime());
            }
        }
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
