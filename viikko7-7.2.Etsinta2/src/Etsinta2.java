
import java.util.Arrays;

public class Etsinta2 {

    public static boolean etsi(int[] taulukko, int k) {
        Arrays.sort(taulukko);
        int summa1;
        int summa2;
        int summa3;
        int summa4;
        for (int i = 0; i < taulukko.length; i++) {
            summa1 = taulukko[i];
            if (summa1 > k) {
                    break;
                }
            //System.out.println("L1: " + taulukko[i]);
            for (int j = 0; j < taulukko.length; j++) {
                summa2 = taulukko[j]+summa1;
                if (summa2 > k) {
                    break;
                }
                //System.out.println("L2: " + taulukko[j]);
                for (int l = 0; l < taulukko.length; l++) {
                    summa3 = taulukko[l]+summa2;
                    if (summa3 > k) {
                        break;
                    }
                    //System.out.println("L3: " + taulukko[l]);
                    for (int m = 0; m < taulukko.length; m++) {
                        summa4 = taulukko[m]+summa3;
                        if (summa4 > k) {
                            break;
                        }
                        //System.out.println("L4: " + taulukko[m]);
                        if (summa4 == k) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    static int[][] esim = new int[][]{{5},
        {2, 3, 5},
        {1, 1, 1, 1},
        {1, 2, 3, 4},
        {4, 2, 3, 1},
        {4, 6, 5, 5},
        {6, 4, 5, 5},
        {6, 6, 6, 4},
        {4, 4, 1, 1, 1, 6, 6},
        {9, 1, 1, 1, 1, 5, 6},
        {4, 3, 1, 5, 5, 6, 6}};

    public static void main(String[] args) {
        for (int[] taulu : esim) {
            System.out.println(Arrays.toString(taulu) + " 10 : " + etsi(taulu, 10));
        }
    }
}
