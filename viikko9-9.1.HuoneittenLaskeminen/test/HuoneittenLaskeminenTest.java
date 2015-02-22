
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("9.1")
public class HuoneittenLaskeminenTest {

    static char[][] mk(String s) {
        String[] lines = s.split("\n");
        char[][] out = new char[lines.length][];
        for (int i = 0; i<lines.length; i++) {
            out[i]=lines[i].toCharArray();
        }
        return out;
    }

    static String unmk(char[][] v) {
        String s = "";
        for (char[] rivi : v) {
            for (char c : rivi) {
                s+=c;
            }
            s+="\n";
        }
        return s;
    }

    static void t(char[][] v, int out) {
        assertEquals("Palautit väärän vastauksen talolle\n"+unmk(v)+"\n",
                     out,
                     HuoneittenLaskeminen.huoneet(v));
    }

    static void t(String s, int out) {
        t(mk(s),out);
    }


    @Test
    public void testaaYksinkertaisia() {

        t(".",1);
        t("..",1);
        t(".#.",2);
        t(".#.#.",3);

        t("#.\n.#",2);

        t("..\n..",1);
        t("...\n.#.\n...",1);
        t("...\n##.\n.#.",2);
        t(".#.\n###\n.#.",4);

    }

    @Test
    public void testaaIsompia() {

        t("..#..#..\n"+
          "##..#.#.\n"+
          ".#.####.\n"+
          "####..#.\n",
                6);

    }

    char[][] v(int h, int w) {
        return new char[h][w];
    }

    boolean c(char[][] v, int i, int j) {
        return !(i>=0 && i<v.length && j>=0 && j<v[0].length
                && v[i][j] == '.');
    }

    boolean c2(char[][] v, int i, int j) {
        return c(v,i,j) && c(v,i-1,j) && c(v,i+1,j) && c(v,i,j-1) && c(v,i,j+1);
    }

    static class C {
        public final int x;
        public final int y;
        public C(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    int mkr(Random r, char[][] v, int tries) {
        int SIZ=5;
        int hu = 0;

        for (int i = 0; i<v.length; i++) {
            for (int j = 0; j<v[0].length; j++) {
                v[i][j] = '#';
            }
        }

        while (tries-->0) {
            int x0 = r.nextInt(v[0].length-SIZ);
            int y0 = r.nextInt(v.length-SIZ);

            ArrayList<C> al = new ArrayList<C>();

            foo: for (int y = 0; y<SIZ; y++) {
                for (int x = 0; x<SIZ; x++) {
                    if (c2(v,y0+y,x0+x)) {
                        al.add(new C(x0+x,y0+y));
                    } else {
                        break foo;
                    }
                }
            }

            if (!al.isEmpty()) {
                hu++;
                for (C c : al) {
                    v[c.y][c.x] = '.';
                }
            }

            /*System.out.println();
            System.out.println(hu);
            System.out.println(unmk(v));*/

        }

        return hu;
    }

    @Test
    public void testaaSatunnaisia() {

        int W = 20;
        int H = 20;
        Random r = new Random();
        for (int tries = 1; tries <40; tries+=3) {
            char[][] v = v(H,W);
            int ret = mkr(r,v,tries);
            t(v,ret);
        }

    }

}