import java.util.Arrays;

public class KnneksiSuurin {

    public static int suurin(int k, int[] luvut) {
        Arrays.sort(luvut);
        return luvut[luvut.length-k];
    }

    static int[] luvut = new int[] {1,64,4,8,45,32,57,31,8234,16,7,44,6,3,74,5,2,744,54};

    public static void main(String[] args) {
        
        System.out.println(suurin(1,luvut));
        System.out.println(suurin(2,luvut));
        System.out.println(suurin(3,luvut));
        System.out.println(suurin(4,luvut));
        System.out.println(suurin(5,luvut));
        System.out.println(suurin(6,luvut));

    }

}