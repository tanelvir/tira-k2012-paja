import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Points("B.5")
public class KirjainruudukkoTest {

    public static char[][] ruu(String s) {
        String[] ss = s.split("\\s+");
        char[][] ruu = new char[ss.length][];
        for (int i=0; i<ruu.length; i++) {
            ruu[i]=ss[i].toCharArray();
        }
        return ruu;
    }

    public static int kr(String s,String sana) {
        return Kirjainruudukko.kirjainRuudukko(ruu(s), sana);
    }

    public static void t(String s, String sana, int exp) {
        assertEquals("Laskit väärin sanan\n"+sana+"\nruudukossa\n"+s+"\n",
                     exp,
                     kr(s,sana));
    }

    @Test
    public void testaaYksiKirjain() {
        t("A","A",1);
        t("AA","A",2);
        t("AA\n"+
          "AA",
          "A",4);
        t("ABCA\n"+
          "BCAB\n"+
          "CABC",
          "A",4);
        t("ABCA\n"+
          "BCAB\n"+
          "CABC",
          "X",0);
    }

    @Test
    public void testaaKaksiKirjainta() {
        t("AB",
          "AB",1);
        t("AB",
          "AC",0);
        t("AB\n"+
          "AB",
          "AB",2);
        t("AB\n"+
          "BA",
          "AB",4);
    }

    @Test
    public void testaaEsimerkit() {
        t("RIRA\n"+
          "ATIA\n"+
          "TATR",
          "TIRA",4);
        t("AA\n"+
          "AA",
          "AAAAAAAAAA", 2048);
    }

    @Test
    public void testaaIso() {
        String r =
            "FFFCFFCCBBDDBFBCFFDCCBFBEEADBAEAFBEDEBBCDECFCBAEDF\n"+
            "DDDDFDBEAFEBBBDBACFFAEABCDDDDBCDABEEDBEFECCECFCDBA\n"+
            "FCDFCCCFDCCCADFAEABCEDABBCCADDEDBBBDDECAEAFCFFCCFD\n"+
            "ECECCACCBCACDBDEEAFBFBBDCADDDAAABADAEBEBAECDEDFFFF\n"+
            "BDACCFEAAFFEFCFDABCBCFDEFDEABACAFCBDEAECCEBCDFFAED\n"+
            "BCECBAFDCBAEFECBDBAEBFEFBCDDDFBDDCADFFDCCBBEBCDEEB\n"+
            "CAFCBFFBAFEFBBCBFCCEDDDCCEAFAFBEDBFFAACACEADCBBEEC\n"+
            "ADFEFCBBCBDECCFBFEEAEDBEDDBFFBECBEDDCFFCEAEECBEFAA\n"+
            "CCFDDABFEAEFBCEFBCEBDCCCBBDBECACCFEEFEBFECCEBDEAFB\n"+
            "CBDBCBBADCFEBCDCFFCADADCBFDEAACDFAFECDAEABCEBDAECC\n"+
            "DABFDFADAFEDEEFACBCDCAFBBDAFCEEBCAAFEEEBFCBEABDBAA\n"+
            "EEFDEBCABDBBFBECDAFEBECAFCFBCACBAFBCBAECFDEADDCEAB\n"+
            "FDCDAAAFFECABDECEDBBFBCDEDFEBBBFCABFECCCADECACDCDE\n"+
            "FEACDFACEBCDACFECACFAAEBFFDEDAEADFDDDBDABDFAFCBEFC\n"+
            "FADCCEFACABBDAAECBDAEADEBAAFADAFCEDCFDEAAACCCFDECA\n"+
            "FEFEBFAEFFCBFDFBADDBBADFFABDBBCABAEFEDAAEEDBBBFAAC\n"+
            "DCBBCEACAABFEABCCEFABCCFDDECDEDEBCDCDDEBDDCCDAACBE\n"+
            "BFEABEDDEBDBDAEFABFAAADCECFBCCAAFBAECECBFBCCCEECFB\n"+
            "CEEFAFEDECBEFDCDAEFEAFADFFFBFDCACAACFCCFCBEDECDCFE\n"+
            "EEABEFFFADDFFFDBBEEEBABCCFEAABEACCCDBAEFEFCABBDAEF\n"+
            "ADFBABADBEDBFCBBBBBFFFFDECAAFEFFBACCADDEDFFFFFABEE\n"+
            "CBADDCDDEFCDAEDAFADDBDACEBAEBBECFDCAFEBCBBCBBBFCDE\n"+
            "BBBABFFBEEAFAECBECFFBFEDEABEBDCEDBDCFCDACAFAEBAFEE\n"+
            "EDEABFDDBFDDEFCADBFAFCBDFECCEDFFFCDEBEDBAABAFDFDCA\n"+
            "AEEAEFDFCBFDFCBDDAFFEDCADFCEDFCDEFADACEEDFFFEEECEF\n"+
            "DCFAEDADABBDEDCADABEFDCACEEDEDECFEDCDCCDDFACFACACC\n"+
            "AAEFCBEDEDBFFFAACDCDCDFDEABDFACAEBCCCCBFCDEFAFDACD\n"+
            "EDDDACDEBBBAEAEAEDADAABEBFFFDBBDEDDBEEBFBFADBEDBAE\n"+
            "BCAAEADFBFCEDDCABEFFEFECBFDCDBDBCFAEAFCFDDBEDCAECF\n"+
            "BDCCECEBEECAEBABAEFCDADEABBDFFBCDADFBCEDAEBFBBCCAC";
        String s = "ABCD";

        ArrayList<String> chars = new ArrayList<String>();
        Collections.addAll(chars, "G","H","J","K");
        Collections.shuffle(chars);

        r=r.replaceAll("A",chars.get(0));
        r=r.replaceAll("B",chars.get(1));
        r=r.replaceAll("C",chars.get(2));
        r=r.replaceAll("D",chars.get(3));
        s=s.replaceAll("A",chars.get(0));
        s=s.replaceAll("B",chars.get(1));
        s=s.replaceAll("C",chars.get(2));
        s=s.replaceAll("D",chars.get(3));

        t(r,s,33);
    }

}