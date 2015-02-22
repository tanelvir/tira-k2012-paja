
public class Tieverkosto {

    public static int[][] etaisyydet(int[][] tiet) {
        // Toteuta minut
        return null;
    }

    public static int[][] esim1 = new int[][] {
        {0,1,0},
        {1,0,2},
        {0,2,0}
    };

    public static int[][] esim2 = new int[][] {
        {0,1,0,0},
        {1,0,0,0},
        {0,0,0,2},
        {0,0,2,0}
    };

    public static int[][] esim3 = new int[][] {
        {0, 1, 7, 0, 0},
        {1, 0, 0, 4, 2},
        {7, 0, 0, 1, 0},
        {0, 4, 1, 0, 1},
        {0, 2, 0, 1, 0}
    };

    public static String arrayToString(int[][] arr) {
        StringBuilder b = new StringBuilder();
        for (int[] rivi : arr) {
            for (int i : rivi) {
                b.append(i).append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    public static void main(String[] args) {
        System.out.println(arrayToString(etaisyydet(esim1)));
        System.out.println(arrayToString(etaisyydet(esim2)));
        System.out.println(arrayToString(etaisyydet(esim3)));
    }

}
