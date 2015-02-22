import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("8.2")
public class NopeaaJarjestamistaTest {

    static void t(int... taul) {
        int[] oikea = taul.clone();
        Arrays.sort(oikea);
        NopeaaJarjestamista.sort(taul);
        assertArrayEquals("Palautit väärän taulukon.",
                          oikea,
                          taul);
    }

    @Test
    public void testaaPienia() {

        t(1,2,3,4,5);
        t(-5,100,-100,1,0,0,0,0,0);
        t(-100,-100,-100,-100,0,100,0,100,1,2);

    }

    static int[] r(Random r, int n) {
        int[] t = new int[n];
        for (int i = 0; i<n; i++) {
            t[i] = r.nextInt(200)-100;
        }
        return t;
    }

    @Test
    public void testaaIsompia() {
        
        Random r = new Random();
        int N = 100;
        int K = 20;

        for (int k=0; k<K; k++) {
            t(r(r,N));
        }
    }

    @Test
    public void testaaAika() {
        
        int N = 10*1000*1000;
        int K = 2;
        Random r = new Random();

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int k=0; k<K; k++) {
            NopeaaJarjestamista.sort(r(r,N));
        }
        double ms = csw.getElapsedTime()*1000/K;
        double raja = 800;
        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                   ms<=raja);

    }
}
