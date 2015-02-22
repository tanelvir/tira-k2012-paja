import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("8.5")
public class SuurinNelioTest {

    static String peltoToString(boolean[][] pelto) {
        StringBuilder sb = new StringBuilder();

        for (boolean[] rivi : pelto) {
            for (boolean b : rivi) {
                if (b) {
                    sb.append(".");
                } else {
                    sb.append("P");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static boolean[][] mk(String... rivit) {
        int n = rivit.length;
        int m = rivit[0].length();
        boolean[][] p = new boolean[n][m];
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                p[i][j] = rivit[i].charAt(j)=='.';
            }
        }
        return p;
    }

    static void t(boolean[][] pelto, int vastaus) {
        assertEquals("Vastasit väärin pellolle:\n"+peltoToString(pelto)+"\n",
                     vastaus,
                     SuurinNelio.suurinNelio(pelto));
    }

    @Test
    public void testaaPienia() {
        t(mk("."),1);
        t(mk(".."),1);
        t(mk("..",
             ".."),2);
        t(mk("..",
             "..",
             ".."),2);
        
        t(mk("P.",
             ".."),1);

        t(mk(".P",
             ".."),1);

        t(mk(".P",
             "P."),1);
        
        t(mk("....",
             "..P.",
             "...."),2);

        t(mk("....",
             "...P",
             "....",
             "....",
             "P..."),3);

    }

    @Test
    public void testaaSuurempia() {

        Random r = new Random();

        for (int n=6; n<20; n+=3) {
            boolean[][] b = new boolean[n][n+2];
            int siz = r.nextInt(5)+1;
            int x0 = r.nextInt(n-siz);
            int y0 = r.nextInt(n-siz);
            for (int x = 0; x<siz; x++) {
                for (int y = 0; y<siz; y++) {
                    b[x0+x][y0+y]=true;
                }
            }

            t(b,siz);
        }

    }

    public static boolean[][] rand(Random r, int n, int m, double bias) {
        boolean[][] b = new boolean[n][m];

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                
                double p = 0.5*i/n + 0.5*j/m;
                p *= bias;
                
                if (r.nextDouble() > p) {
                    b[i][j] = true;
                }

            }
        }

        return b;
    }

    @Test
    public void testaaAika() {

        int N=1000;
        int K=20;

        Random r = new Random();
        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        double ms = 0;
        for (int i = 0; i<K; i++) {
            boolean[][] p = rand(r, N, N, 0.005);
            csw.restart();
            SuurinNelio.suurinNelio(p);
            ms += csw.getElapsedTime()*1000;
        }
        double raja = 450;
        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                   ms<=raja);
    }
}