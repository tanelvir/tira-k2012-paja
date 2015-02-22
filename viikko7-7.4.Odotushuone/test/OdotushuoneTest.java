import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map.Entry;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("7.4")
public class OdotushuoneTest {
    
    static final int MAXH = 1000000;

    private static class J {
        private ArrayList<String> ss = new ArrayList<String>();
        private ArrayList<Integer> xs = new ArrayList<Integer>();
        public void lisaa(String s, int x) {
            ss.add(s);
            xs.add(x);
        }
        public boolean kokeile(String n) {
            int suurin = 0;
            for (int i : xs) {
                if (i > suurin) {
                    suurin = i;
                }
            }
            int ind = ss.indexOf(n);

            if (xs.get(ind) == suurin) {
                xs.remove(ind);
                ss.remove(ind);
                return true;
            } else {
                return false;
            }
        }
    }

    public void testaa(int n) {
        testaa(n, true);
    }

    public void testaa(int n, boolean log) {
        int koko = 0;
        int id = 0;
        
        J j = new J();
        Odotushuone o = new Odotushuone();
        Random r = new Random();

        StringBuilder ops = log ? new StringBuilder() : null;

        for (int i = 0; i<n; i++) {

            int t = r.nextInt(MAXH)+1;
            String nom = "p"+(id++);

            j.lisaa(nom,t);
            o.lisaa(nom,t);
            if (log) {
                ops.append("lisaa("+nom+", "+t+")\n");
            }

            koko++;

            if (i%2==1) {
                String on = o.seuraavaPotilas();
                if (log) {
                    ops.append("seuraavaPotilas()\n");
                }
                assertTrue("Palautit väärän potilaan. "+
                           (log ? "Tehdyt operaatiot olivat:\n"+ops : ""),
                           j.kokeile(on));
                koko--;
            }
        }

        while (koko-->0) {
            String on = o.seuraavaPotilas();
            if (log) {
                ops.append("seuraavaPotilas()\n");

            }
            assertTrue("Palautit väärän potilaan."+
                       (log ? " Tehdyt operaatiot olivat:\n"+ops : ""),
                       j.kokeile(on));
        }
    }

    @Test
    public void testaaPienia() {
        testaa(1);
        testaa(2);
        testaa(3);
        testaa(4);
        testaa(5);
        testaa(6);
    }

    @Test
    public void testaaSuuria() {
        testaa(20);
        testaa(30);
        testaa(40);
    }

    @Test
    public void testaaVielaSuurempia() {
        testaa(500,false);
        testaa(500,false);
    }

    @Test
    public void testaaAikaa() {

        final int N = 500;
        final int K = 400;
        int id = 0;

        Random r = new Random();

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);

        Odotushuone o = new Odotushuone();

        for (int n=0; n<N; n++) {
            for (int k=0; k<K*2; k++) {
                int t = r.nextInt(MAXH)+1;
                String nom = "p"+(id++);
                o.lisaa(nom,t);
            }

            for (int k=0;k <K; k++) {
                o.seuraavaPotilas();
            }
        }

        double ms = csw.getElapsedTime();
        //ms /= N*K*3;

        double raja = 5;

        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms",
                   ms <= raja);

    }

}