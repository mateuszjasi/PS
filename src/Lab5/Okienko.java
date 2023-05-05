package Lab5;

public class Okienko {
    private boolean czyZajete;
    private Interesant interesant;
    private ZdarzenieZakonczenieObslugi zakonczenieObslugi;

    public Okienko(){
        czyZajete=false;
    }

    public boolean isCzyZajete() {
        return czyZajete;
    }

    public void setCzyZajete(boolean czyZajete) {
        this.czyZajete = czyZajete;
    }

    public Interesant getInteresant() {
        return interesant;
    }

    public void setInteresant(Interesant interesant) {
        this.interesant = interesant;
    }

    public ZdarzenieZakonczenieObslugi getZakonczenieObslugi() {
        return zakonczenieObslugi;
    }

    public void setZakonczenieObslugi(ZdarzenieZakonczenieObslugi zakonczenieObslugi) {
        this.zakonczenieObslugi = zakonczenieObslugi;
    }
}
