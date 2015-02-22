import java.util.Scanner;
public class Kirjainruudukko {

    public static int kirjainRuudukko(char[][] ruudukko, String sana) {
        return 0;
    }


    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        System.out.print("Anna korkeus: ");
        int korkeus = Integer.parseInt(lukija.nextLine());
        System.out.print("Anna leveys: ");
        int leveys = Integer.parseInt(lukija.nextLine());
        char[][] ruudukko = new char[korkeus][leveys];
        System.out.println("Anna ruudukko:");
        for (int k = 0; k < korkeus; k++) {
            String rivi = lukija.nextLine();
            for (int l = 0; l < leveys; l++) {
                ruudukko[k][l] = rivi.charAt(l);
            }
        }
        System.out.print("Anna sana: ");
        String sana = lukija.nextLine();

        int vastaus = kirjainRuudukko(ruudukko, sana);

        System.out.println("Tulos: " + vastaus);
    }
}
