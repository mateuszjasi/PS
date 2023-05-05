package Lab4;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNowyInteresant extends BasicSimEvent<Otoczenie, Object> {
    public ZdarzenieNowyInteresant(Otoczenie otoczenie, double delay) throws SimControlException {
        super(otoczenie, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Otoczenie otoczenie = getSimObj();
        Poczta poczta = otoczenie.getPoczta();
        Interesant interesant = new Interesant(otoczenie.getNextNr(), simTime());
        poczta.dodajDoKolejki(interesant);
        new ZdarzenieRozpoczecieObslugi(poczta);
        if(otoczenie.czyDodacInteresanta())
            new ZdarzenieNowyInteresant(otoczenie, otoczenie.getDelay());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
