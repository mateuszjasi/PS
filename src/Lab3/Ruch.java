package Lab3;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class Ruch extends BasicSimEvent<Szlak, Pieszy> {

    public Ruch(Szlak szlak, Pieszy params, double period) throws SimControlException {
        super(szlak, params, period);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Szlak sz = getSimObj();
        sz.opis();
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Pieszy getEventParams() {
        return null;
    }
}
