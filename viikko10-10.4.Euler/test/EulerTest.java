
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("10.4")
public class EulerTest {

    String desc(boolean[][] g) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] rivi : g) {
            for (boolean b : rivi) {
                sb.append(b?1:0);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void check(boolean[][] g, List<Integer> path) {
        String viesti = "Verkko oli:\n"+desc(g)+"Polkusi oli:\n"+path;
        int prev = path.get(path.size()-1);
        boolean[][] c = new boolean[g.length][g.length];
        for (int solmu : path) {
            if (!g[prev][solmu]) {
                if (c[prev][solmu])
                    fail("Käytit seuraavaa kaarta kaksi kertaa: "+prev+" "+solmu+"\n"+viesti);
                else
                    fail("Käytit olematonta kaarta: "+prev+" "+solmu+"\n"+viesti);
            }
            g[prev][solmu] = false;
            g[solmu][prev] = false;
            c[prev][solmu] = true;
            c[prev][solmu] = true;
            prev = solmu;
        }
        for (int i = 0; i<g.length; i++) {
            for (int j = 0; j<g.length; j++) {
                if (g[i][j])
                    fail("Et käyttänyt kaarta: "+i+" "+j+"\n"+viesti);
            }
        }
    }

    public void t(boolean[][] g) {
        System.out.println("test:\n"+desc(g));
        boolean[][] g2 = new boolean[g.length][g.length];
        for (int i = 0; i<g.length; i++)
            System.arraycopy(g[i], 0, g2[i], 0, g.length);

        List<Integer> p = Euler.eulerinKierros(g2);
        check(g, p);
    }

    Random r = new Random();

    public boolean[][] mk1(int n) {
        boolean[][] g = new boolean[n][n];

        base(g);

        return g;
    }

    public void base(boolean[][] g) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i<g.length; i++)
            ids.add(i);
        Collections.shuffle(ids);
        int prev = ids.get(g.length-1);
        for (int i : ids) {
            g[prev][i] = true;
            g[i][prev] = true;
            prev = i;
        }
    }

    public boolean[][] mk2(int n) {
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                g[i][j] = i!=j;
            }
        }

        return g;
    }

    public boolean[][] mk3(int n, int d) {
        int deg = d*2;
        int siz = n*deg;
        int tries = 10;

        System.out.println("mk3 "+n+" "+d);

        loop: while (true) {
            int[] degrees = new int[n];
            boolean[][] g = new boolean[n][n];

            for (int u = 0; u<n; u++) {
                while (degrees[u]<deg) {
                    int v = -1;
                    for (int i = 0; i<tries; i++) {
                        v = r.nextInt(n);
                        if (u!=v && degrees[v]<deg && !g[u][v]) {
                            break;
                        }
                        v = -1;
                    }
                    if (v == -1)
                        continue loop;
                    degrees[u]++;
                    degrees[v]++;
                    g[u][v] = true;
                    g[v][u] = true;
                }
            }
            if (!cn(g))
                continue loop;
            return g;
        }
    }

    @Test
    public void testaaSatunnaisia() {

        for (int n=3; n<12; n+=2) {
            t(mk2(n));
        }

        for (int n=4; n<12; n++) {
            t(mk1(n));
        }

        for (int n=10; n<20; n++) {
            for (int d = 2; d<4; d++) {
                t(mk3(n,d));
            }
        }

    }

    private boolean cn(boolean[][] g) {
        int k[] = new int[g.length];
        cn(g,k,0);
        for (int x : k) {
            if (x<1)
                return false;
        }
        return true;
    }

    private void cn(boolean[][] g, int[] k, int i) {
        if (k[i]>0)
            return;
        k[i]++;
        for (int j = 0; j<g.length; j++) {
            if (g[i][j])
                cn(g,k,j);
        }
    }

}
