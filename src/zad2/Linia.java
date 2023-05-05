package zad2;

public class Linia {
    private double Tk, Tz;

    public Linia(double tz) {
        Tz = tz;
    }
    public void FinishTime(double simtime) {
        Tk = Tz + simtime;
    }
    public double getTime() {
        return Tk;
    }
}
