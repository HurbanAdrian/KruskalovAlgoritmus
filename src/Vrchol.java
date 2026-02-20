import java.util.ArrayList;

public class Vrchol {
    private int cisloVrcholu;
    private int t;

    private Vrchol x;
    private boolean jeDefinitivna;
    private boolean jeRiadiaci;
    private int komponent;

    private ArrayList<OrHrana> hranyVychadzajuce;   //hrany, ktore idu z vrcholu a potom prehladavt len tento arraylist
    public Vrchol(int cisloVrcholu) {
        this.jeRiadiaci = false;
        this.cisloVrcholu = cisloVrcholu;

        this.hranyVychadzajuce = new ArrayList<OrHrana>();
        this.komponent = cisloVrcholu;
    }

    public int getCisloVrcholu() {
        return cisloVrcholu;
    }

    public String toString() {
        return "" + cisloVrcholu;

    }

    public int getT() {
        return t;
    }

    public Vrchol getX() {
        return x;
    }

    public void setT(int t) {
        this.t = t;
    }

    public void setX(Vrchol x) {
        this.x = x;
    }

    public boolean getJeRiadiaci() {
        return jeRiadiaci;
    }

    public void setJeRiadiaci() {
        this.jeRiadiaci = true;
    }

    public void pridajHranu(OrHrana hrana) {
        this.hranyVychadzajuce.add(hrana);
    }

    public Iterable<OrHrana> getHranyVychadzajuce() {
        return this.hranyVychadzajuce;
    }
    public int getKomponent() {
        return komponent;
    }
    public void setKomponent(int komponent) {
        this.komponent = komponent;
    }
}
