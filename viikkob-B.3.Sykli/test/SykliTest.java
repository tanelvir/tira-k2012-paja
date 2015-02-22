import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("B.3")
public class SykliTest {

    public Node l(int n, int m) {
        Node l = new Node();
        Node p = l;
        while (n-- > 0) {
            p.setNext(new Node());
            p=p.getNext();
        }
        Node c = p;
        if (m==0) {
            return l;
        }
        while (--m > 0) {
            p.setNext(new Node());
            p=p.getNext();
        }
        p.setNext(c);
        return l;
    }

    public double t(int n, int m) {
        Node l = l(n,m);
        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        int res = Sykli.sykli(l);
        double ms = csw.getElapsedTime()*1000;
        if (m==0) {
            assertEquals("Palautit väärän tuloksen "+(n+1)+":n mittaiselle syklittömälle listalle.",
                         m,res);
        } else {
            assertEquals("Palautit väärän tuloksen kun listassa oli ensin "+n+" alkiota ja sitten "+m+":n mittainen sykli.",
                         m,res);
        }
        return ms;
    }

    @Test
    public void testaaPienia() {

        for (int i=0; i<10; i++) {
            t(i,0);
        }

        for (int i=0; i<5; i++) {
            for (int j=1; j<6; j++) {
                t(i,j);
            }
        }

    }

    @Test
    public void testaaIsoja() {
        
        int MAX=100000;
        double raja=300;
        double ms;
        for (int i=10; i<=MAX; i*=10) {
            ms = t(3*i,0);
            assertTrue("Käytit aikaa "+ms+"ms joka on yli "+raja+"ms kun syöte oli lista jossa on "+3*i+" alkiota.",
                       ms<=raja);
            for (int j=10; j<=MAX; j*=10) {
                ms = t(i,j);
                assertTrue("Käytit aikaa "+ms+"ms joka on yli "+raja+"ms kun syöte oli lista jossa on ensin "+i+" alkiota ja sitten "+j+":n mittainen sykli.", ms<=raja);
            }
        }

    }

}