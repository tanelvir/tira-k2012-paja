
import java.util.ArrayList;


public class WinCapita {

    public static ArrayList<Integer> vaihda(double[][] kurssit, int s, int t) {
        // Toteuta minut
        return null;
    }

    public static double[][] esim1 = new double[][] {
        {1.0,2.1,3.0},
        {0.4,1.0,1.5},
        {0.3,0.6,1.0}
    };

    public static double[][] esim2 = new double[][]
    {

        {1.0  ,0.420,0.05 ,0.199,0.0},
        {2.314,1.0  ,0.2  ,0.478,0.418},
        {0.0  ,2.450,1.0  ,0.0  ,1.0454},
        {4.753,0.0  ,0.1  ,1.0  ,0.0},
        {5.409,0.0  ,0.0  ,1.027,1.0}

    };

    public static void main(String[] args) {
        System.out.println(vaihda(esim1,0,2));
        System.out.println(vaihda(esim1,2,0));

        System.out.println(vaihda(esim2,0,1)); // Pitäisi olla [0,1]
        System.out.println(vaihda(esim2,2,3)); // Pitäisi olla [2,1,0,3]
        System.out.println(vaihda(esim2,3,4)); // Pitäisi olla [3,1,0,4]
    }

}
