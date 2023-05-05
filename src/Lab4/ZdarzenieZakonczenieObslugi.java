package Lab4;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczenieObslugi extends BasicSimEvent<Poczta, Interesant> {
    public ZdarzenieZakonczenieObslugi(Poczta poczta, double delay, Interesant interesant) throws SimControlException {
        super(poczta, delay, interesant);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();
        poczta.zwolnijOkienko();
        System.out.println("[" + simTimeFormatted() + "] Zwolnienie okienka, Interesant " + getEventParams().getNr() + ", Długość kolejki: " + poczta.getDlugoscKolejki() + ", Okienko zajęte: " + (poczta.getOkienkoZajete()? "Tak":"Nie"));
        poczta.updateCzasPrzebywania(getEventParams().getCzasWejscia());
        if(poczta.czyKolejkaMaInteresantow())
            new ZdarzenieRozpoczecieObslugi(poczta);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Interesant getEventParams() {
        return eventParams;
    }
}
