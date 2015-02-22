import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.HashSet;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;


@Points("7.1")
public class EtsintaTest {

    public static void t(boolean out, int k, int... t) {
        assertEquals("Vastasit väärin tapauksessa etsi("+Arrays.toString(t)+", "+k+")",
                     out,
                     Etsinta.etsi(t,k));
    }

    public static void t2(boolean out, int k, int... t) {
        assertEquals("Vastasit väärin tapauksessa jonka koko on "+t.length,
                     out,
                     Etsinta.etsi(t,k));
    }

    @Test
    public void testaaEsimerkit() {
        t(true, 10,5,5);
        t(false, 10,5,6);
        t(true, 10,5,6,5);
        t(true, 10,4,5,5);
        t(true, 10,6,4,5);
        t(false, 10,6,6,6);
        t(true, 10,4,1,1,6);
        t(true, 10,1,6,4,1);
    }

    public int[] satunnainenF(Random r, int n, int k) {
        int[] t = new int[n];
        HashSet s = new HashSet<Integer>();

        int MAX = 2*k;

        for (int i = 0; i<n; i++) {
            int x;
            do {
                x = r.nextInt(MAX);
            } while (s.contains(x));
            s.add(k-x);
            t[i] = x;
        }
        return t;
    }

    @Test
    public void testaaSatunnaisiaFalse() {
        Random r = new Random();
        int N = 10;
        int KRT = 20;
        for (int i = 0; i<KRT; i++) {
            int k = N+r.nextInt(N);
            t(false, k, satunnainenF(r, N, k));
        }
    }

    public int[] satunnainenT(Random r, int n, int k) {
        int t[] = satunnainenF(r, n, k);
        int i = r.nextInt(n);
        int j;
        do {
            j = r.nextInt(n);
        } while (j==i);

        t[j] = k-t[i];
        return t;
    }

    @Test
    public void testaaSatunnaisiaTrue() {
        Random r = new Random();
        int N = 10;
        int KRT = 20;
        for (int i = 0; i<KRT; i++) {
            int k = N+r.nextInt(N);
            t(true, k, satunnainenT(r, N, k));
        }
    }

    @Test
    public void testaaIsoja() {
        int N = 10000;
        int KRT = 20;
        
        Random r = new Random();

        double ms = 0;
        double raja = 20;

        for (int i = 0; i<KRT; i++) {
            int k = N+r.nextInt(N);
            int[] t = satunnainenT(r, N, k);
            int[] f = satunnainenF(r, N, k);
            CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
            t2(true, k, t);
            t2(false, k, f);
            ms += csw.getElapsedTime()*1000;
        }

        ms /= KRT;

        assertTrue("Käytit aikaa keskimäärin "+ms+"ms, joka on yli "+raja+"ms",
                   ms<=raja);
    }

}
