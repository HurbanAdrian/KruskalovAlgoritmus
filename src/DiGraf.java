import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class DiGraf {
    private ArrayList<Vrchol> vrcholy;
    private ArrayList<OrHrana> orHrany;

    public static final int NEKONECNO = 9999; //Integer.max_VALUE / 2 by mohlo byt tiez

    public DiGraf() {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orHrany = new ArrayList<OrHrana>();

    }

    public DiGraf(int pocetVrcholov) {
        this.vrcholy = new ArrayList<Vrchol>();
        this.orHrany = new ArrayList<OrHrana>();

        for (int i = 1; i <= pocetVrcholov; i++) {
            Vrchol x = new Vrchol(i);
            this.vrcholy.add(x);

        }
    }
    public ArrayList<Vrchol> getVrcholy() {
        return vrcholy;
    }

    public ArrayList<OrHrana> getHrany() {
        return orHrany;
    }

    public void printInfo() {
        System.out.println("Pocet vrcholov: " + vrcholy.size());
        System.out.println("Pocet hran: " + orHrany.size());
    }

    public Vrchol najdiVrchol(int v) {
        return vrcholy.get(v-1);
    }

    //ked z inym grafom robit vytvoris novy subor, ako napr. graf3 teraz
    public static DiGraf nacitajSubor(String nazovSuboru) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(nazovSuboru));
        int pocetVrcholov = 1;
        int pocetHran = 0;
        //najskor len preskenovat

        while (sc.hasNext()) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();

            pocetHran++;
            //2 zapisy rozne ifov
            if (u > pocetVrcholov) {
                pocetVrcholov = u;
            }
            if (v > pocetVrcholov) pocetVrcholov = v;
        }
        sc.close();

        //a az potom zacat pridavat do grafu
        DiGraf g = new DiGraf(pocetVrcholov);

        sc = new Scanner(new FileInputStream(nazovSuboru));
        while (sc.hasNext()) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int c = sc.nextInt();

            OrHrana h = new OrHrana(g.najdiVrchol(u), g.najdiVrchol(v), c);
            g.orHrany.add(h);

        }
        sc.close();
        for (Vrchol vrchol : g.getVrcholy()) {
            for (OrHrana hrana : g.getHrany()) {
                if (hrana.getZac().getCisloVrcholu() == vrchol.getCisloVrcholu()) {
                    vrchol.pridajHranu(hrana);
                    //System.out.println(vrchol.getCisloVrcholu() + " ma hranu " + hrana.getKon().getCisloVrcholu());
                }
            }
        }
        for (Vrchol vrchol : g.getVrcholy()) {
            vrchol.setKomponent(vrchol.getCisloVrcholu());
        }

        return g;
    }

    public void printVrcholy() {
        System.out.print("V = {");
        boolean prvy = true;
        for (Vrchol v : this.vrcholy) {
            if (prvy) prvy = false;
            else System.out.print(",");

            System.out.print("" + v.toString());
        }
        System.out.println("}");
    }

    public void printHrany() {
        System.out.print("H = {");
        boolean prvy = true;
        for (OrHrana h : this.orHrany) {
            if (prvy) prvy = false;
            else System.out.print(",");

            System.out.print("" + h);   //malo by to automaticky ked vie ze potrebuje String
        }
        System.out.println("}");

    }

    public void kruskal(boolean najlacnejsiaKostra) {
        ArrayList<OrHrana> PKostra = new ArrayList<>();
        //zoradenie hran podla ich ohodnotenia do postupnosti P
        ArrayList<OrHrana> zoradeneVzostupne = this.orHrany;
        if (najlacnejsiaKostra) {
            zoradeneVzostupne.sort(Comparator.comparingInt(OrHrana::getCena));
        } else {
            zoradeneVzostupne.sort(Comparator.comparingInt(OrHrana::getCena).reversed());
        }



        //kazdemu vrcholu nastavit komponent
        for (Vrchol v : this.vrcholy) {
            v.setKomponent(v.getCisloVrcholu());
        }
        for (OrHrana hrana : zoradeneVzostupne) {
            hrana.getZac().setKomponent(hrana.getZac().getCisloVrcholu());
            hrana.getKon().setKomponent(hrana.getKon().getCisloVrcholu());
        }

        //zaradzovanie hran do kostry
        for (OrHrana h : zoradeneVzostupne) {
            if (PKostra.size() == this.vrcholy.size() - 1) {
                break;
            }
            if (h.getZac().getKomponent() != h.getKon().getKomponent()) {
                int najnizsiKomponent = Math.min(h.getZac().getKomponent(), h.getKon().getKomponent());
                int premennaKomponent = Math.max(h.getZac().getKomponent(), h.getKon().getKomponent());
                PKostra.add(h);
                for (OrHrana g : zoradeneVzostupne) {
                    if (g.getZac().getKomponent() == premennaKomponent) {
                        g.getZac().setKomponent(najnizsiKomponent);
                    }
                    if (g.getKon().getKomponent() == premennaKomponent) {
                        g.getKon().setKomponent(najnizsiKomponent);
                    }
                }


            }
        }
        //vypisanie kostry + kontrola ci je suvisly
        if (PKostra.size() != this.vrcholy.size() - 1) {
            System.out.println("Kostra neexistuje, graf nie je suvisly");
        } else {    //vypisanie kostry
            int cenaKostry = 0;
            for (OrHrana h : PKostra) {
                cenaKostry += h.getCena();
            }
            System.out.println("Cena kostry: " + cenaKostry);
            System.out.println("Hrany kostry: ");
            for (OrHrana h : PKostra) {
                System.out.println("{" + h.getZac().getCisloVrcholu() + "," + h.getKon().getCisloVrcholu() + "} s cenou " + h.getCena());
            }
        }


    }



    public void printZnacky() {
        System.out.println("Znacky vrcholov:");
        for (Vrchol v : vrcholy) {
            System.out.println("vrchol " + v + " znacka t = " + v.getT() + " ,znacka x = " + v.getX());
        }
    }

}
