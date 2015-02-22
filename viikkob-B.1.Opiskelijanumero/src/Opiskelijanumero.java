import java.util.*;

public class Opiskelijanumero {


    public static int tarkiste(String alkuosa) {
        int loppuluku = 0;
        int x = 1;
        for (int i = alkuosa.length(); i > 0; --i) {
            int n = Character.digit(alkuosa.charAt(i-1), 10);
            if (x==3) {
                x = 1;
                loppuluku += n*x;
            }
            else if (x==1) {
                x = 7;
                loppuluku += n*x;
            }
            else if (x==7) {
                x = 3;
                loppuluku += n*x;
            }
            System.out.println(loppuluku);
        }
            if ((loppuluku%10)==0)
                return 0;
            return 10-(loppuluku%10);
    }

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Alkuosa: ");
        String alku = lukija.nextLine();
        System.out.println("Tarkistusnumero: "+tarkiste(alku));
    }
    

}