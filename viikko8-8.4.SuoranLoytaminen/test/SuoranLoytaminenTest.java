import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.HashSet;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("8.4")
public class SuoranLoytaminenTest {
    
    static void t(boolean b, int[][] p) {
        
        assertEquals("Vastasit väärin syötteelle\n"+Arrays.deepToString(p)+"\n",
                     b,
                     SuoranLoytaminen.suora(p));

    }

    static int[][] mk(int... is) {
        int n = is.length;
        int[][] ret = new int[n/2][2];

        int i = 0;
        for (int j = 0; j<ret.length; j++) {
            ret[j][0] = is[i++];
            ret[j][1] = is[i++];
        }
        return ret;
    }

    @Test
    public void testaaLoytyy() {
        t(true,mk(1,1,1,1));
        t(true,mk(1,1,2,2));
        t(true,mk(1,2,2,4));
        t(true,mk(2,3,1,1,2,4,5,7,8,12));
        t(true,mk(1,30,1,20,4,21,2,55,10,300));
    }

    @Test
    public void testaaEiLoydy() {
        t(false,mk(1,1,2,3));
        t(false,mk(1,2,2,3));
        t(false,mk(2,3,1,1,2,4,5,7,8,13));
        t(false,mk(1,30,1,20,4,21,2,55,10,301));
    }

    static int[][] mkF1(Random r, int n) {
        int[][] ret = new int[n][2];

        int x0 = r.nextInt(20)+1;
        int y0 = r.nextInt(20)+1;
        int k = r.nextInt(3)+1;

        ret[0][0] = x0;
        ret[0][1] = y0;

        for (int i = 1; i<n; i++) {
            ret[i][0] = k*x0;
            ret[i][1] = k*y0+i;
        }

        return ret;
    }

    static int[][] mkF2(Random r, int n) {
        int[][] ret = new int[n][2];

        int x0 = r.nextInt(20)+1;
        int y0 = r.nextInt(20)+1;

        ret[0][0] = x0;
        ret[0][1] = y0;

        int d = -1;
        for (int i = 1; i<n; i++) {
            d *= -1;
            ret[i][0] = (i+1)*x0;
            ret[i][1] = (i+1)*y0+d;
        }

        return ret;
    }

    static int[][] mkT1(Random r, int n) {

        int[][] p = mkF1(r, n);
        int i = r.nextInt(n-1)+1;
        int k = r.nextInt(9)+1;
        p[i][0]= k*p[0][0];
        p[i][1]= k*p[0][1];

        return p;

    }

    static int[][] mkT2(Random r, int n) {

        int[][] p = mkF2(r, n);
        int i = r.nextInt(n-1)+1;
        int k = r.nextInt(9)+1;
        p[i][0]= k*p[0][0];
        p[i][1]= k*p[0][1];

        return p;

    }

    @Test
    public void testaaIsoja() {
        
        int MAX=100;

        Random r = new Random();

        for (int i=5; i<MAX; i+=5) {
            t(false,mkF1(r,i));
            t(false,mkF2(r,i));
            t(true,mkT1(r,i));
            t(true,mkT2(r,i));
        }

    }

}
