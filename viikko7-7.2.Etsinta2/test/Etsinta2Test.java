import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.HashSet;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("7.2")
public class Etsinta2Test {

    public static void t(boolean out, int k, int... t) {
        assertEquals("Vastasit väärin tapauksessa etsi("+Arrays.toString(t)+", "+k+")",
                     out,
                     Etsinta2.etsi(t,k));
    }




    @Test
    public void testaaEsimerkit() {
        t(false,10,5);
        t(true,10,2,3);
        t(false,10,1,1,1,1);
        t(true,10,1,2,3,4);
        t(true,10,4,2,3,1);
        t(false,10,4,6,5,5);
        t(false,10,6,4,5,5);
        t(false,10,6,6,6,4);
        t(true,10,4,4,1,1,1,6,6);
        t(false,10,9,1,1,1,1,5,6);
        t(true,10,4,3,1,5,5,6,6);
    }


    @Test
    public void testaaIsompia() {
        t(true,12,7, 14, 2, 13, -20, 13, 17, 8, 16, 9);
        t(true,15,15, 20, 11, 15, 2, 12, 6, 17, -22, 4);
        t(true,16, 0, 2, 2, 13, -7, 13, 17, 5, 5, 5);
        t(true,17, 11, 20, 18, 14, 17, 20, 6, 21, 6, -36);
        t(true,19, 27, 14, 11, 2, 11, 18, 21, 9, 2, -12);

        t(false,16, 16, 16, 2, 5, 9, 15, 19, 16, 18, 13);
        t(false,14, 9, 7, 3, 6, 7, 7, 17, 10, 16, 16);
        t(false,14, 16, 15, 19, 15, 5, 11, 20, 8, 10, 13);
        t(false,18, 14, 6, 19, 5, 6, 24, 11, 22, 26, 17);
        t(false,15, 13, 4, 14, 16, 0, 4, 17, 14, 17, 10);
    }
}
