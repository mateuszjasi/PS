package Lab5;

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
        Interesant interesant = new Interesant(otoczenie.getNextNr(), null);
        interesant.setKoniecCierpliwosci(new ZdarzenieKoniecCierpliwosci(poczta, otoczenie.getPoczta().getCzasCierpliwosci(), interesant));
        if(poczta.dodajDoKolejki(interesant)){
            new ZdarzenieRozpoczecieObslugi(poczta);
            if(otoczenie.czyMoznaDodacInteresanta()){
                new ZdarzenieNowyInteresant(otoczenie, otoczenie.getDelay());
            }
            else {
                stopSimulation(10);
            }
        }
        else {
            poczta.incStraty();
            poczta.updateStraty();
            interesant.terminujKoniecCierpliwosci();
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
