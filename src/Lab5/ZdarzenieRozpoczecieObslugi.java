package Lab5;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczecieObslugi extends BasicSimEvent<Poczta, Object> {

    public ZdarzenieRozpoczecieObslugi(Poczta poczta) throws SimControlException {
        super(poczta);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();
        if(!poczta.czyWszystkieZajete()){
            Interesant interesant = null;
            try {
                interesant = poczta.pobierzZKolejki();
                interesant.terminujKoniecCierpliwosci();
                poczta.zajmijOkienko(interesant, poczta.getStanZdatnosci() ? new ZdarzenieZakonczenieObslugi(poczta, poczta.generujCzasObslugi(), interesant) : null);
                System.out.println("Do okienka podchodzi interesant nr: "+interesant.getNr() +" czas symulacji: "+simTimeFormatted());

            } catch (InterruptedException e) {
                e.printStackTrace();
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
