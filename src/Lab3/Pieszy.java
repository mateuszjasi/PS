package Lab3;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Pieszy extends BasicSimObj {
    private int id, nrOdcinka;
    private double predkosc, przebytaDroga, liczbaOdcinkow, dlugoscSzlaku, ostatniKrok, czasWejscia, dlugoscOdcinka;
    public Pieszy(int id, double predkosc, double dlugoscSzlaku, double liczbaOdcinkow) {
        this.id = id;
        this.predkosc = predkosc;
        this.przebytaDroga = 0;
        this.dlugoscSzlaku = dlugoscSzlaku;
        this.ostatniKrok = simTime();
        this.czasWejscia = simTime();
        this.liczbaOdcinkow = liczbaOdcinkow;
        this.nrOdcinka = 0;
        this.dlugoscOdcinka = dlugoscSzlaku / liczbaOdcinkow;
    }
    public double przebytaDroga(double dlugosc){
        przebytaDroga = przebytaDroga + predkosc * (simTime() - ostatniKrok);
        ostatniKrok = simTime();
        if (przebytaDroga < dlugosc) {
            if((int)(przebytaDroga / dlugoscOdcinka) + 1 > nrOdcinka){
                nrOdcinka++;
                System.out.println(simTime() + " Ruch, Pieszy " + id +", Pozycja: " +  nrOdcinka + ", Przebyta droga: " + przebytaDroga);
            }
            return -1.0;
        }
        else {
            System.out.println(simTime() + " Odchodzi, Pieszy " + id + ", Przebyta droga: " + przebytaDroga + ", czas przemarszu: " + (simTime() - czasWejscia));
            return simTime() - czasWejscia;
        }

    }
    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
