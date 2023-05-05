package Lab4;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Interesant extends BasicSimObj {
    private int nr;
    private double czasWejscia;

    public Interesant(int nr, double czasWejscia) {
        this.nr = nr;
        this.czasWejscia = czasWejscia;
    }

    public int getNr() {
        return nr;
    }

    public double getCzasWejscia() {
        return czasWejscia;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
