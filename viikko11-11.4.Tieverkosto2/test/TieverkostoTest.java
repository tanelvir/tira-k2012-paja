import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("11.4")
public class TieverkostoTest {
    public static String desc(int[][] vuori) {
        int max = 0;
        for (int[] rivi : vuori) {
            for (int i : rivi) {
                max = Math.max(max,i);
            }
        }
        int maxlen = (""+max).length();

        StringBuilder sb = new StringBuilder();

        for (int[] rivi : vuori) {
            for (int i : rivi) {
                sb.append(String.format("%"+maxlen+"d",i)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static int[][] mk(int n, Integer... ints) {
        int[][] ret = new int[n][n];
        int ind = 0;
        if (ints.length != n*n) {
            throw new Error("APUA!");
        }
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                ret[i][j] = ints[ind++];
            }
        }
        return ret;
    }

    public static Tieverkosto T(int[][] in){
        return new Tieverkosto(in);
    }

    public static void te(int[][] in, Tieverkosto T, int i, int j, int exp) {
        String sin = desc(in);
        int out = T.etaisyys(i,j);
        assertEquals("Kaupunkien "+i+" ja "+j+" välinen etäisyys ei ollut oikea. Tiet:\n"+sin,
                exp,
                out);
    }

    public static void tr(int[][] in, Tieverkosto T, int i, int j, ArrayList<Integer> exp) {
        String sin = desc(in);
        ArrayList<Integer> out = T.reitti(i,j);
        assertEquals("Kaupunkien "+i+" ja "+j+" välinen reitti ei ollut oikea. Tiet:\n"+sin,
                exp,
                out);
    }

    static ArrayList<Integer> l(Integer... is) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Collections.addAll(ret, is);
        return ret;
    }

    @Test
    public void testaaPienia() {
        int[][] t1 =
                mk(3,
                0,1,0,
                1,0,2,
                0,2,0);
        Tieverkosto T1 = T(t1);
        te(t1,T1,0,2,3);
        tr(t1,T1,0,2,l(0,1,2));

        int[][] t2 =
                mk(4,
                0,2,4,8,
                2,0,8,16,
                4,8,0,32,
                8,16,32,0);
        Tieverkosto T2 = T(t2);
        te(t2,T2,1,2,6);
        tr(t2,T2,1,2,l(1,0,2));
        te(t2,T2,2,3,12);
        tr(t2,T2,2,3,l(2,0,3));

        int[][] t3 =
                mk(5,
                0, 1, 7, 0, 0,
                1, 0, 0, 4, 2,
                7, 0, 0, 1, 0,
                0, 4, 1, 0, 1,
                0, 2, 0, 1, 0);
        Tieverkosto T3 = T(t3);
        te(t3,T3,2,4,2);
        tr(t3,T3,2,4,l(2,3,4));
        te(t3,T3,0,2,5);
        tr(t3,T3,0,2,l(0,1,4,3,2));

        int[][] t4 = mk(
                5,
                0, 1, 0, 6, 0,
                1, 0, 1, 0, 0,
                0, 1, 0, 1, 5,
                6, 0, 1, 0, 1,
                0, 0, 5, 1, 0);
        Tieverkosto T4 = T(t4);
        te(t4,T4,0,4,4);
        tr(t4,T4,0,4,l(0,1,2,3,4));
        te(t4,T4,4,0,4);
        tr(t4,T4,4,0,l(4,3,2,1,0));

    }

    @Test
    public void testaaIsompia() {
        int[][] t1 =
                mk(10,
                0,8,2,3,4,7,8,1,3,3,
                8,0,1,5,2,1,5,0,3,0,
                2,1,0,0,8,2,3,6,7,5,
                3,5,0,0,9,9,4,1,6,2,
                4,2,8,9,0,1,6,2,7,6,
                7,1,2,9,1,0,0,9,0,6,
                8,5,3,4,6,0,0,4,6,5,
                1,0,6,1,2,9,4,0,8,7,
                3,3,7,6,7,0,6,8,0,6,
                3,0,5,2,6,6,5,7,6,0);
        Tieverkosto T1 = T(t1);
        te(t1,T1,0,1,3);
        tr(t1,T1,0,1,l(0,2,1));
        te(t1,T1,1,2,1);
        tr(t1,T1,1,2,l(1,2));
        te(t1,T1,3,8,5);
        tr(t1,T1,3,8,l(3,7,0,8));
        te(t1,T1,3,9,2);
        tr(t1,T1,3,9,l(3,9));
        te(t1,T1,7,5,3);
        tr(t1,T1,7,5,l(7,4,5));


        int[][] t2 =
                mk(8,
                0  ,0  ,525,0  ,959,827,517,493,
                0  ,0  ,114,813,0  ,448,96 ,989,
                525,114,0  ,945,310,601,909,121,
                0  ,813,945,0  ,902,217,585,0  ,
                959,0  ,310,902,0  ,662,472,0  ,
                827,448,601,217,662,0  ,136,862,
                517,96 ,909,585,472,136,0  ,389,
                493,989,121,0  ,0  ,862,389,0);

        Tieverkosto T2 = T(t2);

        te(t2,T2,0,1,613);
        tr(t2,T2,0,1,l(0,6,1));
        te(t2,T2,3,4,825);
        tr(t2,T2,3,4,l(3,5,6,4));
        te(t2,T2,7,1,235);
        tr(t2,T2,7,1,l(7,2,1));

    }
}
