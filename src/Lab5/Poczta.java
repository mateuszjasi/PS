package Lab5;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Poczta extends BasicSimObj {
    private RNGenerator rng;
    private double mi, rho, p;
    private ArrayBlockingQueue<Interesant> kolejka;
    private int maxOkienek, licznikStrat;
    private ObiektAwaryjny systemKomputerowy;
    private MonitoredVar czasPrzebywania, dlugoscKolejki, zajetosc, straty;
    private ArrayList<Okienko> okienka;

    public Poczta(double mi, MonitoredVar czasPrzebywania, MonitoredVar dlugoscKolejki, MonitoredVar zajetosc, MonitoredVar straty,
                  ObiektAwaryjny systemKomputerowy, int maxDlugoscKolejki, int ileOkienek, double p, double rho){
        rng = new RNGenerator(RNGenerator.generateSeed());
        this.mi = mi;
        kolejka = new ArrayBlockingQueue<>(maxDlugoscKolejki);
        this.okienka = new ArrayList<>();
        for(int i=0; i<ileOkienek;i++)
            okienka.add(new Okienko());
        this.maxOkienek=ileOkienek;
        this.systemKomputerowy = systemKomputerowy;
        this.czasPrzebywania = czasPrzebywania;
        this.dlugoscKolejki = dlugoscKolejki;
        this.straty=straty;
        this.zajetosc = zajetosc;
        this.p = p;
        this.rho = rho;
        this.licznikStrat=0;
    }

    public double getCzasCierpliwosci(){
        return rng.exponential(rho);
    }

    public ArrayList<Okienko> getOkienka() {
        return okienka;
    }

    public int getLicznikStrat() {
        return licznikStrat;
    }

    public boolean getStanZdatnosci(){
        return systemKomputerowy.czyZdatny();
    }

    public void zajmijOkienko(Interesant interesant, ZdarzenieZakonczenieObslugi zakonczenieObslugi){
        for(Okienko okienko : okienka){
            if(!okienko.isCzyZajete()){
                System.out.println("Do okienka podchodzi interesant nr: "+interesant.getNr() +" czas symulacji: "+simTimeFormatted());
                okienko.setCzyZajete(true);
                okienko.setInteresant(interesant);
                okienko.setZakonczenieObslugi(zakonczenieObslugi);
                updateZajetosc();
                return;
            }
        }

    }

    public void zwolnijOkienko(Interesant interesant){
        for(Okienko okienko : okienka){
            if(okienko.getInteresant()!=null){
                if(okienko.getInteresant().equals(interesant)){
                    okienko.setCzyZajete(false);
                    okienko.setInteresant(null);
                    okienko.setZakonczenieObslugi(null);
                    updateZajetosc();
                    return;
                }
            }

        }
        updateZajetosc();
    }

    public boolean czyWracaDoKolejki(){
        return rng.nextDouble() < p;
    }

    public boolean czyWszystkieZajete(){
        boolean czyZajete=true;
        for(Okienko okienko : okienka)
            if(!okienko.isCzyZajete()) czyZajete=false;
        return czyZajete;
    }

    public boolean dodajDoKolejki(Interesant interesant){
        if(kolejka.offer(interesant)){
            updateDlugpscKolejki();
            System.out.println("Do kolejki wchodzi interesant nr: "+interesant.getNr() +" czas symulacji: "+simTimeFormatted());
            return true;
        }
        else {
            licznikStrat++;
            updateStraty();
            System.out.println("Kolejka jest pełna! Stracono interesanta nr: "+interesant.getNr() +" czas symulacji: "+simTimeFormatted());
            return false;
        }
    }

    public Interesant pobierzZKolejki() throws InterruptedException {
        if(czyKolejkaMaInteresantow()){
            Interesant interesantZKolejki = kolejka.take();
            updateDlugpscKolejki();
            return interesantZKolejki;
        }else return null;
    }

    public void wyjdzZKolejki(Interesant interesant){
        kolejka.remove(interesant);
        licznikStrat++;
        updateStraty();
    }

    public boolean czyKolejkaMaInteresantow(){
        return  kolejka.size() > 0;
    }

    public void updateCzasPrzebywania(double czasWejscia){
        czasPrzebywania.setValue(simTime()-czasWejscia);
    }

    public void updateDlugpscKolejki(){
        dlugoscKolejki.setValue(kolejka.size());
    }

    public void updateZajetosc(){
        int ileZajete=0;
        for(Okienko okienko : okienka)
            if(okienko.isCzyZajete()) ileZajete++;
        zajetosc.setValue(ileZajete);
    }

    public void incStraty(){
        licznikStrat++;
    }

    public void updateStraty(){ straty.setValue(licznikStrat);}

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }

    public double generujCzasObslugi() {
        return rng.exponential(mi);
    }
}