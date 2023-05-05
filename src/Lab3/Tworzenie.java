package Lab3;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Tworzenie extends BasicSimEvent<Szlak,Pieszy> {
    private double czasPojawienia, a, b;
    RNGenerator rng = new RNGenerator();
    public Tworzenie(Szlak szlak, Pieszy params, double period, double a, double b, double czasPojawienia) throws SimControlException {
        super(szlak, params, period);
        this.a = a;
        this.b = b;
        this.czasPojawienia = czasPojawienia;
    }

    @Override
    protected void stateChange() throws SimControlException {
        if(simTime() >= czasPojawienia) {
            Szlak szlak = getSimObj();
            szlak.nowy_pieszy(getRunTime());
            czasPojawienia += rng.uniform(a, b);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Pieszy getEventParams() {
        return null;
    }
}
