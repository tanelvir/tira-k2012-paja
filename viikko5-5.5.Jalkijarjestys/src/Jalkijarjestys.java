import java.util.Arrays;

public class Jalkijarjestys {

    public static int[] jalkijarjestys(int[] esi, int[] sisa) {
        return new int[0];
    }


    public static void main(String[] args) {
        int[] esi1 = new int[] {7,9,3,5,2};
        int[] sisa1 = new int[] {3,9,5,7,2};

        System.out.println("E: "+Arrays.toString(esi1)+"\nS: "+Arrays.toString(sisa1)
                           +"\nJ: "+Arrays.toString(jalkijarjestys(esi1,sisa1)));

        int[] esi2 = new int[] {0, 1, 3, 4, 5, 10, 6, 2, 7, 8, 9};
        int[] sisa2 = new int[] {3, 1, 10, 5, 4, 6, 0, 7, 2, 9, 8};

        System.out.println("E: "+Arrays.toString(esi2)+"\nS: "+Arrays.toString(sisa2)
                           +"\nJ: "+Arrays.toString(jalkijarjestys(esi2,sisa2)));
    }

}