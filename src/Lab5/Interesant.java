package Lab5;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

public class Interesant extends BasicSimObj {
    private int nr;
    private double czasWejscia;
    private ZdarzenieKoniecCierpliwosci koniecCierpliwosci;

    public Interesant(int nr, ZdarzenieKoniecCierpliwosci koniecCierpliwosci){
        this.nr = nr;
        czasWejscia=simTime();
        this.koniecCierpliwosci=koniecCierpliwosci;
    }

    public int getNr() {
        return nr;
    }

    public double getCzasWejscia() {
        return czasWejscia;
    }

    public void setKoniecCierpliwosci(ZdarzenieKoniecCierpliwosci koniecCierpliwosci){
        this.koniecCierpliwosci=koniecCierpliwosci;
    }

    public void terminujKoniecCierpliwosci() throws SimControlException {
        koniecCierpliwosci.terminate();
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
