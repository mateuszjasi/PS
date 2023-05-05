package Lab4;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Poczta extends BasicSimObj {
    private RNGenerator rng;
    private double mi;
    private ArrayList<Interesant> kolejka;
    private boolean okienkoZajete;
    private MonitoredVar czasPrzebywania, dlugoscKolejki, zajetosc;

    public Poczta(RNGenerator rng, double mi, MonitoredVar czasPrzebywania, MonitoredVar dlugoscKolejki, MonitoredVar zajetosc) {
        this.rng = rng;
        this.mi = mi;
        kolejka = new ArrayList<>();
        okienkoZajete = false;
        this.czasPrzebywania = czasPrzebywania;
        this.dlugoscKolejki = dlugoscKolejki;
        this.zajetosc = zajetosc;
    }

    public boolean getOkienkoZajete() {
        return okienkoZajete;
    }

    public int getDlugoscKolejki() {
        return kolejka.size();
    }

    public void zajmijOkienko() {
        okienkoZajete = true;
        updateZajetosc();
    }

    public void zwolnijOkienko() {
        okienkoZajete = false;
        updateZajetosc();
    }

    public boolean dodajDoKolejki(Interesant interesant) {
        kolejka.add(interesant);
        updateDlugoscKolejki();
        System.out.println("[" + simTimeFormatted() + "] Dołączenie do kolejki, Interesant " + interesant.getNr() + ", Długość kolejki: " + kolejka.size() + ", Okienko zajęte: " + (okienkoZajete? "Tak":"Nie"));
        return true;
    }

    public Interesant pobierzZKolejki() {
        if(czyKolejkaMaInteresantow()) {
            Interesant interesantZKolejki = kolejka.remove(0);
            updateDlugoscKolejki();
            return interesantZKolejki;
        } else
            return null;
    }

    public boolean czyKolejkaMaInteresantow() {
        return  kolejka.size() > 0;
    }

    public double generujCzasObslugi() {
        return rng.exponential(mi);
    }

    public void updateCzasPrzebywania(double czasWejscia) {
        czasPrzebywania.setValue(simTime() - czasWejscia);
    }

    public void updateDlugoscKolejki() {
        dlugoscKolejki.setValue(kolejka.size());
    }

    public void updateZajetosc() {
        zajetosc.setValue(okienkoZajete ? 1 : 0);
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}