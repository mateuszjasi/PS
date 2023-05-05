package Lab5;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczenieObslugi extends BasicSimEvent<Poczta, Interesant> {

    public ZdarzenieZakonczenieObslugi(Poczta poczta, double delay, Interesant interesant) throws SimControlException {
        super(poczta, delay, interesant);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();
        Interesant interesant = getEventParams();
        poczta.zwolnijOkienko(interesant);
        System.out.println("Od okienka odchodzi interesant nr: "+interesant.getNr() +" czas symulacji: "+simTimeFormatted());

        if(poczta.czyKolejkaMaInteresantow())
            new ZdarzenieRozpoczecieObslugi(poczta);
        if(poczta.czyWracaDoKolejki()){
            if(poczta.dodajDoKolejki(interesant)){
                interesant.setKoniecCierpliwosci(new ZdarzenieKoniecCierpliwosci(poczta, poczta.getCzasCierpliwosci(), interesant));
            }
        }
        else {
            poczta.updateCzasPrzebywania(interesant.getCzasWejscia());
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Interesant getEventParams() {
        return eventParams;
    }
}
