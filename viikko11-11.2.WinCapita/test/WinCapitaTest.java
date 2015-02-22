
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("11.2")
public class WinCapitaTest {
    public static String desc(double[][] k) {

        StringBuilder sb = new StringBuilder();

        for (double[] rivi : k) {
            for (double i : rivi) {
                sb.append(String.format("%10f",i)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static double[][] mk(int n, Double... ds) {
        double[][] ret = new double[n][n];
        int ind = 0;
        if (ds.length != n*n) {
            throw new Error("APUA! "+ds.length+" "+n);
        }
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                ret[i][j] = ds[ind++];
            }
        }
        return ret;
    }

    static double[][] c(double[][] in) {
        double[][] ret = new double[in.length][in.length];
        for (int i = 0; i<in.length; i++) {
            System.arraycopy(in[i], 0, ret[i], 0, in.length);
        }
        return ret;
    }

    static ArrayList<Integer> l(Integer... is) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Collections.addAll(ret, is);
        return ret;
    }

    public static void t(double[][] kurssit, int s,int t,ArrayList<Integer> exp) {
        assertEquals("Vastasit väärin. Kurssit:\n"+desc(kurssit)+"\ns: "+s+" t: "+t,
                exp,
                WinCapita.vaihda(kurssit, s, t));
    }

    @Test public void testaaYksinkertaisia() {
        for (int i = 0; i<2; i++) {
            for (int j=i+1; j<3; j++) {
                t(mk(3,
                        1.0, 0.5, 0.5,
                        0.5, 1.0, 0.5,
                        0.5, 0.5, 1.0),
                        i,j,
                        l(i,j)
                        );
            }
        }

        // TODO


    }

    @Test public void testaaEsimerkki2() {
        double[][] esim2 = new double[][]
        {
            {1.0  ,0.420,0.05 ,0.199,0.0},
            {2.314,1.0  ,0.2  ,0.478,0.418},
            {0.0  ,2.450,1.0  ,0.0  ,1.0454},
            {4.753,0.0  ,0.1  ,1.0  ,0.0},
            {5.409,0.0  ,0.0  ,1.027,1.0}
        };

        t(c(esim2),
                0,1,
                l(0,1));
        t(c(esim2),
                1,0,
                l(1,0));
        t(c(esim2),
                0,2,
                l(0,1,2));
        t(c(esim2),
                2,0,
                l(2,1,0));
        t(c(esim2),
                2,3,
                l(2,1,0,3));
        t(c(esim2),
                3,2,
                l(3,1,2));
        t(c(esim2),
                3,4,
                l(3,1,0,4));
        t(c(esim2),
                4,3,
                l(4,1,0,3));
    }

    @Test
    public void testaaIsompaa() {
        double[][] k = mk(8,
                1.000000,   0.936850,   1.685454,   1.283262,   1.522487,   2.319174,   1.563203,   1.432243,
                1.058454,   1.000000,   1.783855,   1.386668,   1.598791,   2.440654,   1.644512,   1.565629,
                0.586148,   0.557885,   1.000000,   0.781577,   0.913424,   1.366233,   0.892213,   0.997251,
                0.737528,   0.678764,   1.262355,   1.000000,   1.170614,   1.761846,   1.146832,   1.281948,
                0.603008,   0.564470,   1.069806,   0.849152,   1.000000,   1.525492,   1.041668,   1.114665,
                0.385689,   0.270204,   0.681769,   0.531952,   0.651985,   1.000000,   0.679243,   0.740055,
                0.549889,   0.418934,   0.956213,   0.755349,   0.944266,   1.462894,   1.000000,   1.100908,
                0.090793,   0.361423,   0.869002,   0.684642,   0.783033,   1.311000,   0.896849,   1.000000);

        t(c(k),7,2,l(7,6,4,3,2));
        t(c(k),2,7,l(2,3,4,5,6,7));
        t(c(k),0,7,l(0,1,2,3,4,5,6,7));
        t(c(k),1,6,l(1,2,3,4,5,6));
        t(c(k),6,1,l(6,4,3,2,0,1));
        t(c(k),4,5,l(4,5));
        t(c(k),5,4,l(5,4));

    }

}
