import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("B.2")
public class StirlingTest {

    public void t(long n, long k, long exp) {
        assertEquals("stirling("+n+","+k+")",
                     exp,
                     Stirling.stirling(n,k));
    }

    @Test public void testaaReunaehdot() {
        t(0,0,1);
        t(0,1,0);
        t(0,2,0);
        t(0,20,0);
    }

    @Test public void testaaPienia() {
        t(1,1,1);
        t(2,1,1);
        t(3,1,1);

        t(2,2,1);
        t(3,2,3);
        t(4,2,7);
        t(5,2,15);
        t(6,2,31);

        t(3,3,1);
        t(4,3,6);
        t(5,3,25);
        t(6,3,90);
    }

    @Test public void testaaIsoja() {

        t(10,5,42525);
        t(15,5,210766920);
        t(20,5,749206090500L);

        t(15,10,12662650);
        t(20,10,5917584964655L);
        t(25,10,1203163392175387500L);
    }

}
