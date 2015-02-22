import java.util.Arrays;

public class Young {

    public static boolean etsi(int[][] young, int k) {
        for (int i = 0; i < young.length; i++) {
            int taulu[] = young[i];
            int index = Arrays.binarySearch(taulu, k);
            if (index>=0)
                return true;
        }
        return false;
    }

    static final int M = Integer.MAX_VALUE;

    static int[][] young1 = new int[][] {{1,2,4},
                                         {3,5,6},
                                         {10,M,M}};

    static int[][] young2 = new int[][] {{2,5,11,27},
                                         {7,15,16,M},
                                         {21,M,M,M}};

    static int[][] young3 = new int[][] {{1,2,3,4,5},
                                         {2,3,4,5,6},
                                         {3,4,5,6,7},
                                         {4,5,6,7,8},
                                         {5,6,7,8,M}};

    public static void main(String[] args) {
        System.out.println(etsi(young1,5));
        System.out.println(etsi(young1,6));
        System.out.println(etsi(young1,7));

        System.out.println(etsi(young2,5));
        System.out.println(etsi(young2,15));
        System.out.println(etsi(young2,17));

        System.out.println(etsi(young3,8));
        System.out.println(etsi(young3,9));
    }

}