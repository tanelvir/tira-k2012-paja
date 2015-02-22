import java.util.Scanner;

public class Ohjelma {

    public static void main(String[] args) {
        String teksti = "pekka pekka simo pekka simo heikki aaro aarne pekka";
        Scanner lukija = new Scanner(teksti);
        SanaTilasto st = new SanaTilasto(lukija);
        System.out.println("sanoja: "+st.eriSanojenLukumaara());
        System.out.println("pekka: "+st.frekvenssi("pekka"));
        System.out.println("simo: "+st.frekvenssi("simo"));
        System.out.println("aaro: "+st.frekvenssi("aaro"));
    }

}
