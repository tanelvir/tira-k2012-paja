import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.HashSet;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("8.3")
public class KnneksiSuurinTest {

    static int s(int k, int[] luvut) {
        int[] toiset = luvut.clone();
        Arrays.sort(toiset);
        return toiset[toiset.length-k];
    }

    static void t(int k, int[] luvut) {
        assertEquals("Vastasit väärin syötteelle\n"+Arrays.toString(luvut)+"\n",
                     s(k,luvut),
                     KnneksiSuurin.suurin(k,luvut));
    }

    static int[] mk(int n, Random r) {
        HashSet<Integer> hs = new HashSet<Integer>();
        while (hs.size()<n) {
            hs.add(r.nextInt());
        }

        int[] ret = new int[n];
        int i = 0;
        for (int x : hs) {
            ret[i++]=x;
        }
        return ret;

    }

    public void testaa(int n, Random r) {
        
        int[] luvut = mk(n,r);
        for (int i=1; i<=n; i++) {
            t(i,luvut);
        }
    }

    @Test
    public void testaaPienia() {
        Random r = new Random();
        testaa(10,r);
    }

    @Test
    public void testaaSuuria() {
        Random r = new Random();
        testaa(100,r);
    }

    @Test
    public void testaaPieniKAika() {

        int N = 100000;
        Random r = new Random();
        int[] luvut = mk(N,r);

        int K = 20;
        int[] vastaukset = new int[K];
        for (int i=0; i<K; i++) {
            vastaukset[i]=s(i+1,luvut);
        }

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int i=0; i<K; i++) {
            assertEquals("Vastasit väärin isolle syötteelle.",
                         vastaukset[i],
                         KnneksiSuurin.suurin(i+1,luvut));
        }
        double ms = csw.getElapsedTime()*1000;
        double raja = 250;
        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                   ms<=raja);

    }

    @Test
    public void testaaSuuriKAika() {
        int N = 10000;
        Random r = new Random();
        int[] luvut = mk(N,r);

        int K = 20;
        int[] vastaukset = new int[K];
        for (int i=0; i<K; i++) {
            vastaukset[i]=s(N-i,luvut);
        }

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int i=0; i<K; i++) {
            assertEquals("Vastasit väärin isolle syötteelle.",
                         vastaukset[i],
                         KnneksiSuurin.suurin(N-i,luvut));
        }
        double ms = csw.getElapsedTime()*1000;
        double raja = 100;
        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                   ms<=raja);

    }

}