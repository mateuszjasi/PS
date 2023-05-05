package Lab5;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class Otoczenie extends BasicSimObj {
    private RNGenerator rng;
    private Poczta poczta;
    private double lambda;
    private int licznikInteresantow, maxInteresantow;

    public Otoczenie(Poczta poczta, double lambda, int maxInteresantow) {
        rng = new RNGenerator(RNGenerator.generateSeed());
        this.poczta = poczta;
        this.lambda = lambda;
        licznikInteresantow = 0;
        this.maxInteresantow = maxInteresantow;
    }

    public int getLicznikInteresantow() {
        return licznikInteresantow;
    }

    public Poczta getPoczta(){
        return poczta;
    }

    public double getDelay(){
        return rng.exponential(lambda);
    }

    public int getNextNr(){
        return licznikInteresantow++;
    }

    public boolean czyMoznaDodacInteresanta(){
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
