
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("P.1")
public class OsasummaTest {


    Random r = new Random();
    int MIN=-100;
    int MAX=100;

    public int[] r(int n) {
        int[] ret = new int[n];
        for (int i = 0; i<ret.length; i++) {
            ret[i] = r.nextInt(MAX-MIN)+MIN;
        }
        return ret;
    }

    @Test public void testaaOperaatiot() {
        int KRT = 10;
        int N = 10;
        for (int krt = 1; krt<KRT; krt++) {
            int n = N*krt;
            int[] t = r(n);
            String str = Arrays.toString(t);
            Osasumma o = new Osasumma(t);

            for (int i = 0; i<n; i++) {
                assertEquals("Taulukolle\n"+str+"\noperaatio get("+i+")\n",
                        t[i],
                        o.get(i));
            }

            for (int i = 0; i<n; i++) {
                for (int j = i; j<n; j++) {
                    int ans = 0;
                    for (int k = i; k<=j; k++) {
                        ans += t[k];
                    }

                    assertEquals("Taulukolle\n"+str+"\noperaatio osasumma("+i+","+j+")\n",
                        ans,
                        o.osasumma(i,j));
                }
            }

        }
    }

    @Test public void testaaAika() {
        int N = 1000000;
        System.out.println("N: "+N);

        Osasumma o = new Osasumma(r(N));

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int i = 0; i<N; i+=101) {
            for (int j = i; j<N; j+=99) {
                o.osasumma(i,j);
            }
        }
        double ms = csw.getElapsedTime()*1000;
        double raja = 200;

        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                ms<=raja);



    }

}