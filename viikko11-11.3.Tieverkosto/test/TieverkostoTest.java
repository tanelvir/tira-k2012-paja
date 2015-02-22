
import fi.helsinki.cs.tmc.edutestutils.Points;
import static org.junit.Assert.*;
import org.junit.Test;


@Points("11.3")
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

    public static void t(int[][] in, int[][] exp) {
        String sin = desc(in);
        String sexp = desc(exp);
        int[][] out = Tieverkosto.etaisyydet(in);
        assertArrayEquals("Syöte oli:\n"+sin+"Tulosteen olisi pitänyt olla:\n"+sexp+"\nTuloste oli:\n"+desc(out),
                exp,
                out);
    }

    @Test
    public void testaaPienia() {
        t(
                mk(3,
                0,1,0,
                1,0,2,
                0,2,0),
                mk(3,
                0,1,3,
                1,0,2,
                3,2,0));
        t(
                mk(4,
                0,1,0,0,
                1,0,0,0,
                0,0,0,2,
                0,0,2,0),
                mk(4,
                0,1,0,0,
                1,0,0,0,
                0,0,0,2,
                0,0,2,0));

        t(
                mk(4,
                0,2,4,8,
                2,0,8,16,
                4,8,0,32,
                8,16,32,0),
                mk(4,
                0,2,4,8,
                2,0,6,10,
                4,6,0,12,
                8,10,12,0
                ));

        t(
                mk(5,
                0, 1, 7, 0, 0,
                1, 0, 0, 4, 2,
                7, 0, 0, 1, 0,
                0, 4, 1, 0, 1,
                0, 2, 0, 1, 0),
                mk(5,
                0, 1, 5, 4, 3,
                1, 0, 4, 3, 2,
                5, 4, 0, 1, 2,
                4, 3, 1, 0, 1,
                3, 2, 2, 1, 0
                ));

    }

    @Test
    public void testaaIsompia() {
        t(
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
                3,0,5,2,6,6,5,7,6,0
                ),
                mk(10,
                0,3,2,2,3,4,5,1,3,3,
                3,0,1,5,2,1,4,4,3,6,
                2,1,0,4,3,2,3,3,4,5,
                2,5,4,0,3,4,4,1,5,2,
                3,2,3,3,0,1,6,2,5,5,
                4,1,2,4,1,0,5,3,4,6,
                5,4,3,4,6,5,0,4,6,5,
                1,4,3,1,2,3,4,0,4,3,
                3,3,4,5,5,4,6,4,0,6,
                3,6,5,2,5,6,5,3,6,0
                ));

        t(
                mk(8,
                   0,211,525,902,959,827,517,493,
                   211,0,114,813,838,448,96,989,
                   525,114,0,945,310,601,909,121,
                   902,813,945,0,902,217,585,316,
                   959,838,310,902,0,662,472,293,
                   827,448,601,217,662,0,136,862,
                   517,96,909,585,472,136,0,389,
                   493,989,121,316,293,862,389,0),
                mk(8,
                   0,211,325,660,635,443,307,446,
                   211,0,114,449,424,232,96,235,
                   325,114,0,437,310,346,210,121,
                   660,449,437,0,609,217,353,316,
                   635,424,310,609,0,608,472,293,
                   443,232,346,217,608,0,136,467,
                   307,96,210,353,472,136,0,331,
                   446,235,121,316,293,467,331,0)
          );
    }

}
