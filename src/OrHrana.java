public class OrHrana {
    private Vrchol zac, kon;    //zalezi na poradi
    private int cena;

    public OrHrana(Vrchol u, Vrchol v, int cena) {
        this.zac = u;
        this.kon = v;
        this.cena = cena;
    }
    //ked nefunguje tak repairIde
    public Vrchol getZac() {
        return zac;
    }

    public Vrchol getKon() {
        return kon;
    }

    public int getCena() {
        return cena;
    }

    public String toString() {
        return "{" + zac.getCisloVrcholu() + "," + kon.getCisloVrcholu() + "}";

    }

}
