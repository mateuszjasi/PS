package Lab5;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;

public class ZdarzenieAwaria extends BasicSimEvent<ObiektAwaryjny, Object> {
    public ZdarzenieAwaria(ObiektAwaryjny obiekt, double delay) throws SimControlException {
        super(obiekt, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        ObiektAwaryjny obiekt = getSimObj();
        obiekt.setStanZdatnosci(false);
        obiekt.updateStanZdatnosci();
        ArrayList<Okienko> okienka = obiekt.getPoczta().getOkienka();
        System.out.println("AWARIA" +" czas symulacji: "+simTimeFormatted());
        for(Okienko okienko : okienka){
            if(okienko.isCzyZajete()){
                okienko.getZakonczenieObslugi().terminate();
                okienko.setZakonczenieObslugi(null);
            }
        }
        new ZdarzenieNaprawa(obiekt, obiekt.generujCzasNiezdatnosci());
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
