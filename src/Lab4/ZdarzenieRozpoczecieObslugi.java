package Lab4;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczecieObslugi extends BasicSimEvent<Poczta, Object> {
    public ZdarzenieRozpoczecieObslugi(Poczta poczta) throws SimControlException {
        super(poczta);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();
        if(!poczta.getOkienkoZajete()) {
            Interesant interesanci = poczta.pobierzZKolejki();
            if(interesanci != null) {
                poczta.zajmijOkienko();
                System.out.println("[" + simTimeFormatted() + "] Zajęcie okienka, Interesant " + interesanci.getNr() + ", Długość kolejki: " + poczta.getDlugoscKolejki() + ", Okienko zajęte: " + (poczta.getOkienkoZajete()? "Tak":"Nie"));
                new ZdarzenieZakonczenieObslugi(poczta, poczta.generujCzasObslugi(), interesanci);
            }
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
