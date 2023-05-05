package Lab5;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

public class ObiektAwaryjny extends BasicSimObj {
    private RNGenerator rng;
    private double alfa, beta;
    private boolean stanZdatnosci;
    private Poczta poczta;
    private MonitoredVar stanZdatnosciMonitor;

    public ObiektAwaryjny(double alfa, double beta, MonitoredVar stanZdatnosciMonitor){
        this.rng = new RNGenerator(RNGenerator.generateSeed());
        this.alfa = alfa;
        this.beta = beta;
        this.stanZdatnosci = true;
        this.stanZdatnosciMonitor = stanZdatnosciMonitor;
    }

    public Poczta getPoczta() {
        return poczta;
    }

    public void setPoczta(Poczta poczta) {
        this.poczta = poczta;
    }

    public double generujCzasZdatnosci(){
        return rng.exponential(beta);
    }

    public double generujCzasNiezdatnosci(){
        return rng.exponential(alfa);
    }

    public boolean czyZdatny(){
        return stanZdatnosci;
    }

    public void setStanZdatnosci(boolean stan){
        this.stanZdatnosci=stan;
    }

    public void updateStanZdatnosci(){
        stanZdatnosciMonitor.setValue(stanZdatnosci ? 1 : 0);
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
