package Lab5;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieKoniecCierpliwosci extends BasicSimEvent<Poczta, Interesant> {

    public ZdarzenieKoniecCierpliwosci(Poczta poczta, double delay, Interesant interesant) throws SimControlException{
        super(poczta, delay, interesant);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();
        System.out.println("ZNIECIERPLIWIENIE Stracono interesanta nr: "+getEventParams().getNr() +" czas symulacji: "+simTimeFormatted());
        poczta.wyjdzZKolejki(getEventParams());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Interesant getEventParams() {
        return eventParams;
    }
}
