
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("9.2")
public class HuoneittenKootTest {

    static char[][] mk(String s) {
        String[] lines = s.split("\n");
        char[][] out = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            out[i] = lines[i].toCharArray();
        }
        return out;
    }

    static String unmk(char[][] v) {
        String s = "";
        for (char[] rivi : v) {
            for (char c : rivi) {
                s += c;
            }
            s += "\n";
        }
        return s;
    }

    static void t(char[][] v, ArrayList<Integer> out) {
        String viesti = "Palautit väärän vastauksen talolle\n" + unmk(v) + "\n";
        ArrayList<Integer> ret = HuoneittenKoot.koot(v);
        Collections.sort(out);
        Collections.sort(ret);
        assertEquals(viesti,
                out,
                ret);
    }

    static void t(String s, Integer... out) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        Collections.addAll(a,out);
        t(mk(s), a);
    }

    @Test
    public void testaaYksinkertaisia() {

        t(".", 1);
        t("..", 2);
        t(".#.", 1,1);
        t(".#.#.", 1,1,1);

        t("#.\n.#", 1,1);

        t("..\n..", 4);
        t("...\n.#.\n...", 8);
        t("...\n##.\n.#.", 1,5);
        t(".#.\n###\n.#.", 1,1,1,1);

    }

    @Test
    public void testaaIsompia() {

        t("..#..#..\n"
        + "##..#.#.\n"
        + ".#.####.\n"
        + "####..#.\n",
                2,1,5,1,2,5);

    }

    char[][] v(int h, int w) {
        return new char[h][w];
    }

    boolean c(char[][] v, int i, int j) {
        return !(i >= 0 && i < v.length && j >= 0 && j < v[0].length
                && v[i][j] == '.');
    }

    boolean c2(char[][] v, int i, int j) {
        return c(v, i, j) && c(v, i - 1, j) && c(v, i + 1, j) && c(v, i, j - 1) && c(v, i, j + 1);
    }

    static class C {

        public final int x;
        public final int y;

        public C(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    ArrayList<Integer> mkr(Random r, char[][] v, int tries) {
        int SIZ = 5;
        ArrayList<Integer> koot = new ArrayList<Integer>();

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                v[i][j] = '#';
            }
        }

        while (tries-- > 0) {
            int x0 = r.nextInt(v[0].length - SIZ);
            int y0 = r.nextInt(v.length - SIZ);

            ArrayList<C> al = new ArrayList<C>();

            foo:
            for (int y = 0; y < SIZ; y++) {
                for (int x = 0; x < SIZ; x++) {
                    if (c2(v, y0 + y, x0 + x)) {
                        al.add(new C(x0 + x, y0 + y));
                    } else {
                        break foo;
                    }
                }
            }

            if (!al.isEmpty()) {
                koot.add(al.size());
                for (C c : al) {
                    v[c.y][c.x] = '.';
                }
            }

            /*
             * System.out.println(); System.out.println(hu);
            System.out.println(unmk(v));
             */

        }

        return koot;
    }

    @Test
    public void testaaSatunnaisia() {

        int W = 20;
        int H = 20;
        Random r = new Random();
        for (int tries = 1; tries < 40; tries += 3) {
            char[][] v = v(H, W);
            ArrayList<Integer> ret = mkr(r, v, tries);
            t(v, ret);
        }

    }
}
