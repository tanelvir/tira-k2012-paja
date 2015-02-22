import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("7.5")
public class VankilapakoTest {
    
    static char[][] mk(String s) {
        String[] lines = s.split("\n");
        char[][] out = new char[lines.length][];
        for (int i = 0; i<lines.length; i++) {
            out[i]=lines[i].toCharArray();
        }
        return out;
    }

    static void t(String s, String out) {
        assertEquals("Palautit väärän reitin vankilalle\n"+s+"\n",
                     out,
                     new String(Vankilapako.pakene(mk(s))));
    }

    static void t(char[][] s, String out) {
        assertEquals("Palautit väärän reitin vankilalle\n"+s+"\n",
                     out,
                     new String(Vankilapako.pakene(s)));
    }

    @Test
    public void testaaYksinkertaisia() {
        t(".....\n"+
          "..X..\n"+
          ".....\n"+
          ".....",
          "Y");
        t(".###.\n"+
          ".#X#.\n"+
          ".....\n"+
          ".....",
          "AA");
        t(".....\n"+
          ".....\n"+
          "..X..\n"+
          ".....",
          "A");
        t("#####\n"+
          "#####\n"+
          "##X##\n"+
          "#####",
          "A");
        t("#####\n"+
          "##.##\n"+
          "#.X.#\n"+
          "#####",
          "A");
        t("#####\n"+
          "#####\n"+
          "..X##\n"+
          "#####",
          "VV");
        t("#.###\n"+
          "#.###\n"+
          "#.X##\n"+
          "#####",
          "VYY");
    }

    @Test public void testaaKaytava() {
        for (int N=5; N<=20; N+=5) {
            String s = "";
            String out = "";
            for (int i = 0; i<N; i++) {
                s+="##.#\n";
                out+="Y";
            }
            s+="##X#\n";
            s+="####\n";
            t(s,out);
        }
    }

    @Test public void testaaKaytava2() {
        for (int N=5; N<=20; N+=5) {
            String s = "";
            String out = "";
            for (int i = 0; i<N; i++) {
                s+="##.#\n";
                out+="Y";
            }
            s=s+"##X#"+s+"####\n";
            t(s,out);
        }
    }
}