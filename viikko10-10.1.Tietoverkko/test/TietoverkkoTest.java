
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("10.1")
public class TietoverkkoTest {

    public class E {
        public final int u;
        public final int v;
        public E(int u, int v) {
            this.u=u;
            this.v=v;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final E other = (E) obj;
            if (this.u != other.u) {
                return false;
            }
            if (this.v != other.v) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + this.u;
            hash = 71 * hash + this.v;
            return hash;
        }

    }

    Random r = new Random();

    public boolean[][] mk(int n, Collection<E> es) {
        boolean[][] g = new boolean[n][n];
        for (E e : es) {
            g[e.u][e.v] = true;
            g[e.v][e.u] = true;
        }
        return g;
    }

    public HashSet<E> conn(ArrayList<Integer> ids) {
        HashSet<E> ret = new HashSet<E>();
        for (int i = 1; i<ids.size(); i++) {
            int v = ids.get(i);
            int u = ids.get(r.nextInt(i));
            ret.add(new E(u,v));
        }
        return ret;
    }

    public boolean[][] mkT(int n) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i<n; i++) {
            ids.add(i);
        }
        Collections.shuffle(ids);
        return mk(n, conn(ids));
    }

    public boolean[][] mkF(int n) {
        int a = r.nextInt(n-1)+1;
        int b = n-a;
        ArrayList<Integer> aids = new ArrayList<Integer>();
        ArrayList<Integer> bids = new ArrayList<Integer>();
        for (int i = 0; i<n; i++) {
            if (i<a) {
                aids.add(i);
            } else {
                bids.add(i);
            }
        }
        HashSet<E> ga = conn(aids);
        HashSet<E> gb = conn(bids);
        ga.addAll(gb);
        return mk(n, ga);
    }

    public void t(boolean exp, boolean[][] g) {
        assertEquals("XXX",
                exp,
                Tietoverkko.tietoverkko(g));
    }

    @Test public void testaaPienia() {
        t(true,mkT(1));
        t(true,mkT(2));
        t(true,mkT(3));
        t(true,mkT(4));
        t(false,mkF(2));
        t(false,mkF(3));
        t(false,mkF(4));
    }

    @Test public void testaaKeskisuuria() {
        for (int i = 8; i<15; i++) {
            for (int j = 0; j<5; j++) {
                t(true,mkT(i));
                t(false,mkF(i));
            }
        }
    }

    @Test public void testaaSuuria() {
        int N = 250;
        for (int k = 0; k<20; k++) {
            t(true,mkT(N));
            t(false,mkF(N));
        }
    }

}
