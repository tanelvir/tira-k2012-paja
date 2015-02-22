import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.HashMap;

@Points("5.2")
public class SanaTilastoTest {

    @Test public void testaaLyhyt() {

        String teksti = "a a a a b b b a a b b c c d d e f g h";
        
        SanaTilasto st = new SanaTilasto(new Scanner(teksti));

        String v1 = "Palautit väärän frekvenssin sanalle ";
        String v2 = " tekstissä \""+teksti+"\"";

        assertEquals(v1+"a"+v2,
                     6,
                     st.frekvenssi("a"));
        assertEquals(v1+"b"+v2,
                     5,
                     st.frekvenssi("b"));
        assertEquals(v1+"e"+v2,
                     1,
                     st.frekvenssi("e"));
        assertEquals(v1+"x"+v2,
                     0,
                     st.frekvenssi("x"));
    }

    public SanaTilasto rev(HashMap<String,Integer> frek) {
        ArrayList<String> al = new ArrayList<String>();

        for (String s : frek.keySet()) {
            int n = frek.get(s);
            for (int i = 0; i<n; i++) {
                al.add(s);
            }
        }

        Collections.shuffle(al);

        StringBuilder b = new StringBuilder();

        for (String s : al) {
            b.append(s).append(" ");
        }

        return new SanaTilasto(new Scanner(b.toString()));
    }

    public HashMap<String,Integer> randomfrek(int n) {
        
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        Random r = new Random();

        for (int i = 0; i<n; i++) {
            String word = new BigInteger(64,r).toString(32);
            hm.put(word,r.nextInt(100)+1);
        }

        return hm;

    }

    @Test public void testaaSatunnaisia() {

        for (int i = 0; i<20; i++) {
            
            HashMap<String,Integer> h = randomfrek(30);
            SanaTilasto st = rev(h);

            assertEquals("Palautit väärän sanojen lukumäärän",
                         h.size(),
                         st.eriSanojenLukumaara());

            for (String s : h.keySet()) {
                assertEquals("Palautit väärän frekvenssin.",
                             (int)h.get(s),
                             st.frekvenssi(s));
            }

        }

    }

    @Test public void testaaSatunnaisiaIsoja() {

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        double aika = 0;

        for (int i = 0; i<5; i++) {
            
            HashMap<String,Integer> h = randomfrek(1500);

            csw.restart();
            SanaTilasto st = rev(h);

            assertEquals("Palautit väärän sanojen lukumäärän",
                         h.size(),
                         st.eriSanojenLukumaara());

            for (String s : h.keySet()) {
                assertEquals("Palautit väärän frekvenssin.",
                             (int)h.get(s),
                             st.frekvenssi(s));
            }

            aika+=csw.getElapsedTime();

        }

        double ms = aika*1000;

        double raja = 2000;

        assertTrue("Käytit aikaa "+ms+"ms joka on yli "+raja+"ms",ms<=raja);

    }
}