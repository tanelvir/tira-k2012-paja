import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("B.1")
public class OpiskelijanumeroTest {
    
    public void t(String alku, int tark) {
        assertEquals("tarkiste("+alku+")", tark, Opiskelijanumero.tarkiste(alku));
    }

    @Test public void testaaYksinkertaiset() {
        t("00001",3);
        t("00010",7);
        t("00100",9);
        t("01000",3);
        t("10000",7);
        t("11000",0);
        t("01100",2);
        t("00110",6);
        t("00011",0);
    }

    @Test public void testaaEsimerkit() {
        t("01274913",9);
        t("01525594",7);
        t("1",3);
        t("1234567891011121314151617181920",4);
    }

    @Test public void testaaPitkia() {
        String s = "123456789101112131415161718192";
        t(s+s,6);
        t(s+s+s,4);
        t(s+s+s+s,2);
        t(s+s+s+s+s,0);
        t(s+s+s+s+s+s+s+s+s+s,0);
        t(s+s+s+s+s+s+s+s+s+s+s,8);
    }

}