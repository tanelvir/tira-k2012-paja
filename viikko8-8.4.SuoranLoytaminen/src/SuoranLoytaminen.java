import java.util.Arrays;

public class SuoranLoytaminen {
    
    public static boolean suora(int[][] pisteet) {
        double taulu[] = new double[pisteet.length];
        for (int i = 0; i < pisteet.length; i++) {
            taulu[i] = ((double)pisteet[i][0]/pisteet[i][1]);
        }
        Arrays.sort(taulu);
        
        for (int i = 0; i < taulu.length-1; i++) {
            if (taulu[i]==taulu[i+1]) {
                return true;
            }
        }
        return false;
    }
    static int[][] esim1 = new int[][]{{1, 1}, {2, 2}, {2, 3}};
    static int[][] esim2 = new int[][]{{1, 2}, {2, 3}, {3, 4}};
    static int[][] esim3 = new int[][]{{1, 10}, {3, 31}, {3, 29}};
    static int[][] esim4 = new int[][]{{1, 10}, {3, 30}, {3, 31}};
    
    public static void main(String[] args) {
        
        System.out.println(suora(esim1));
        System.out.println(suora(esim2));
        System.out.println(suora(esim3));
        System.out.println(suora(esim4));
        
    }
}
    