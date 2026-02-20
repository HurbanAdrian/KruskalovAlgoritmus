import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //DiGraf g = DiGraf.nacitajSubor("C:\\Users\\AMD Ryzen\\OneDrive - Žilinská univerzita v Žiline\\AtG\\KruskalovAlgoritmus\\test.txt");
        DiGraf g = DiGraf.nacitajSubor("C:\\Users\\hurban\\OneDrive - Žilinská univerzita v Žiline\\AtG\\KruskalovAlgoritmus\\graf_kruskal_1500.txt");  //doma
        //DiGraf g = DiGraf.nacitajSubor("C:\\Users\\AMD Ryzen\\OneDrive - Žilinská univerzita v Žiline\\AtG\\AtG_2_7.3\\grafLimitTesting.txt");
        //DiGraf g = DiGraf.nacitajSubor("C:\\Users\\hurban\\OneDrive - Žilinská univerzita v Žiline\\AtG\\LabelSet\\digraf_1500_20.txt");
        //DiGraf g = DiGraf.nacitajSubor("C:\\Users\\hurban\\OneDrive - Žilinská univerzita v Žiline\\AtG\\KruskalovAlgoritmus\\graf_kruskal_1500.txt");
        g.printInfo();
        g.printVrcholy();
        g.printHrany();
        g.kruskal(true);
    }
}