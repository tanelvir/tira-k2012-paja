
public class Paaohjelma {

    public static int[][] esim1 = new int[][] {
        {0, 1, 7, 0, 0},
        {1, 0, 0, 4, 2},
        {7, 0, 0, 1, 0},
        {0, 4, 1, 0, 1},
        {0, 2, 0, 1, 0}
    };

    public static void main(String[] args) {
        Tieverkosto t1 = new Tieverkosto(esim1);
        System.out.println("0--3: "+t1.etaisyys(0,3)+" "+t1.reitti(0,3));
        System.out.println("2--0: "+t1.etaisyys(2,0)+" "+t1.reitti(2,0));

    }
}
