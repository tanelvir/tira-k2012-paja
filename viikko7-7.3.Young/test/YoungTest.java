import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.HashSet;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("7.3")
public class YoungTest {

    static final int M = Integer.MAX_VALUE;

    static int[][] mk(int w, int h, int... vals) {
        int[][] ret = new int[h][w];

        int i = 0;
        for (int y=0; y<h; y++) {
            for (int x=0; x<w; x++) {
                ret[y][x]=vals[i++];
            }
        }
        return ret;
    }

    public static void t(boolean out, int k, int[][] y) {
        assertEquals("Vastasit väärin kun haettiin lukua "+k+" taulukosta\n"+Arrays.deepToString(y)+"\n",
                     out,
                     Young.etsi(y,k));
    }

    @Test public void testaaPienia() {

        t(true,1,mk(1,1,1));
        t(false,1,mk(1,1,2));
        
        t(true,1,mk(2,2,1,2,3,M));
        t(true,2,mk(2,2,1,2,3,M));
        t(true,3,mk(2,2,1,2,3,M));
        t(false,4,mk(2,2,1,2,3,M));

    }

    public static int[][] gen(Random r, int w, int h, int step) {
        int[][] ret = new int[h][w];
        for (int y=0; y<h; y++) {
            for (int x=0; x<w; x++) {
                int min = 0;
                if (y > 0) {
                    min = ret[y-1][x];
                }
                if (x > 0) {
                    min = Math.max(min, ret[y][x-1]);
                }
                
                ret[y][x] = min+1+r.nextInt(step);
            }
        }
        return ret;
    }

    public static boolean contains(int[][] y, int k) {
        for (int[] xs : y) {
            for (int x : xs) {
                if (x==k)
                    return true;
            }
        }
        return false;
    }

    public static void t2(boolean out, int k, int[][] y) {
        assertEquals("Vastasit väärin isolle instanssille.",
                     out,
                     Young.etsi(y,k));
    }


    public double testaaKoko(int K, int KRT) {

        Random r = new Random();

        final int STEP=100;

        double ms=0;

        int[][] y = gen(r,K,K,STEP);

        int[] falses = new int[KRT];

        for (int i=0; i<KRT; i++) {
            do {
                falses[i] = r.nextInt(2*K*STEP);
            } while (contains(y,falses[i]));
        }

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);

        for (int i=0; i<KRT; i++) {
            int k = y[r.nextInt(K)][r.nextInt(K)];
            t2(true, k, y);
        }

        int k = 0;
        for (int i=0; i<KRT; i++) {
            t2(false, falses[k++], y);
        }

        ms = csw.getElapsedTime()*1000;
        return ms/(2*KRT);

    }

    @Test public void testaaIsompia() {
        testaaKoko(5, 10);
        testaaKoko(10, 10);
        testaaKoko(20, 10);
    }

    @Test public void testaaAika() {

        double raja = 1.0;
        double ms = testaaKoko(3000,200);

        assertTrue("Käytit aikaa keskimäärin "+ms+"ms joka on yli "+raja+"ms",
                   ms<=raja);

    }

}
