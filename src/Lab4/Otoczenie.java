package Lab4;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class Otoczenie extends BasicSimObj {
    private RNGenerator rng;
    private Poczta poczta;
    private double lambda;
    private int licznikInteresantow;
    private int maxInteresantow;

    public Otoczenie(RNGenerator rng, Poczta poczta, double lambda, int maxInteresantow) {
        this.rng = rng;
        this.poczta = poczta;
        this.lambda = lambda;
        this.maxInteresantow = maxInteresantow;
        licznikInteresantow = 0;
    }

    public int getMaxInteresantow() {
        return maxInteresantow - 1;
    }

    public Poczta getPoczta() {
        return poczta;
    }

    public double getDelay() {
        return rng.exponential(lambda);
    }

    public int getNextNr() {
        return licznikInteresantow++;
    }

    public boolean czyDodacInteresanta() {
        return licznikInteresantow < maxInteresantow;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
