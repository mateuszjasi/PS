package Lab5;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;

public class ZdarzenieNaprawa extends BasicSimEvent<ObiektAwaryjny, Object> {
    public ZdarzenieNaprawa(ObiektAwaryjny obiekt, double delay) throws SimControlException {
        super(obiekt, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        ObiektAwaryjny obiekt = getSimObj();
        obiekt.setStanZdatnosci(true);
        obiekt.updateStanZdatnosci();
        Poczta poczta = obiekt.getPoczta();
        ArrayList<Okienko> okienka = poczta.getOkienka();
        System.out.println("NAPRAWA" +" czas symulacji: "+simTimeFormatted());
        for(Okienko okienko : okienka){
            if(okienko.isCzyZajete()) {
                okienko.setZakonczenieObslugi(new ZdarzenieZakonczenieObslugi(poczta, poczta.generujCzasObslugi(), okienko.getInteresant()));
            }
        }
        new ZdarzenieAwaria(obiekt, obiekt.generujCzasZdatnosci());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }


    @Override
    public Object getEventParams() {
        return null;
    }
}
