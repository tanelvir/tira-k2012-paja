
import java.util.*;

public class Etsinta {

    public static boolean etsi(int[] taulukko, int k) {
        Arrays.sort(taulukko);
        int luku = -1;
        for (int i = 0; i < taulukko.length; i++) {
            luku = Arrays.binarySearch(taulukko, (k - taulukko[i]));
            if (luku >= 0 && luku!=i) {
                System.out.println(luku);
                return true;
            }
        }
        return false;
    }
    static int[][] esim = new int[][]{{5, 5},
        {5, 6},
        {5, 6, 5},
        {4, 5, 5},
        {6, 4, 5},
        {6, 6, 6},
        {4, 1, 1, 6},
        {5, 9, 1, 19, 4, 16, 18, 17, 13, 0}};

    public static void main(String[] args) {
        for (int[] taulu : esim) {
            System.out.println(Arrays.toString(taulu) + " 11 : " + etsi(taulu, 11));
        }
    }
}
