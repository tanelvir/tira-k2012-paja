
import java.util.Arrays;

public class NopeaaJarjestamista {

    public static void sort(int[] taulukko) {
        if (taulukko.length == 0) {
            return;
        }

        int suurin = taulukko[0], pienin = taulukko[0];
        for (int i = 1; i < taulukko.length; i++) {
            if (taulukko[i] > suurin) {
                suurin = taulukko[i];
            } else if (taulukko[i] < pienin) {
                pienin = taulukko[i];
            }
        }
        int numerot = suurin - pienin + 1;
        int[] maara = new int[numerot];
        for (int i = 0; i < taulukko.length; i++) {
            maara[taulukko[i] - pienin]++;
        }
        int alkio = 0;
        for (int i = 0; i < numerot; i++) {
            for (int j = 0; j < maara[i]; j++) {
                taulukko[alkio] = i + pienin;
                alkio++;
            }
        }
    }

    public static void main(String[] args) {

        int taulu[] = {7, 3, 5, 6, 3, 2, 1};
        sort(taulu);
        for (int i = 0; i < taulu.length; i++) {
            System.out.println(taulu[i]);
        }

    }
}