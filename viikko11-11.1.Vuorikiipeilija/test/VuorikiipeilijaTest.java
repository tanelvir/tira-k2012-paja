
import fi.helsinki.cs.tmc.edutestutils.Points;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("11.1")
public class VuorikiipeilijaTest {

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

    static int[][] mk(int n, int m, Integer... ints) {
        int[][] ret = new int[n][m];
        int ind = 0;
        if (ints.length != n*m) {
            throw new Error("APUA!");
        }
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                ret[i][j] = ints[ind++];
            }
        }
        return ret;
    }

    public static void t(int exp, int[][] vuori) {
        String d = desc(vuori);
        assertEquals("Vastasit väärin vuorelle:\n"+d,
                exp,
                Vuorikiipeilija.kiipea(vuori));
    }

    @Test public void testaaYksinkertaisia() {

        t(0,
                mk(2,2,
                1,1,
                9,1
                ));

        t(0,
                mk(2,2,
                100,100,
                9999999,100));

        t(5,
                mk(2,3,
                1,2,4,
                9,9,6));

        t(5,
                mk(2,3,
                1,9,9,
                2,4,6));

        t(5,
                mk(2,3,
                1,2,9,
                9,4,6));
        t(21,
                mk(4,4,
                1 , 5,10,15,
                21,22,22,20,
                21,22,22,25,
                21,21,21,20));

        t(0,
                mk(5,5,
                1,1,1,1,1,
                9,9,9,9,1,
                1,1,1,1,1,
                1,9,9,9,9,
                1,1,1,1,1));

    }

    @Test public void testaaIsompia() {

        t(29,
                mk(10,10,
                5,4,9,6,4,0,7,3,1,5,
                8,7,8,0,8,9,5,8,7,7,
                2,1,2,3,9,0,7,1,1,3,
                3,5,0,6,5,8,3,4,9,9,
                4,2,8,9,1,3,4,1,6,6,
                7,1,2,9,1,7,3,3,0,1,
                8,5,3,4,6,0,7,5,1,7,
                1,0,6,1,2,9,4,8,4,5,
                3,3,7,6,7,0,6,8,8,7,
                3,0,5,2,6,6,5,7,6,4));

        t(2203,
                mk(7,8,
                879,909,149,624,356,468,900,868,
                211,732,605,874,552,862,234,510,
                525,114,111,587,700,602,876,463,
                902,813,945,856,681,841,796,761,
                959,838,310,902,234,474,15,356,
                827,448,601,217,662,218,248,632,
                517,96,909,585,472,136,346,892));


    }

}
