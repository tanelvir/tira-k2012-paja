
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

@Points("P.4")
public class HamiltonTest {

    Random r = new Random();

    public String descr(TreeMap<Integer,TreeSet<Integer>> g) {
        String s = "";
        for (Integer v : g.keySet()) {
            s += v +": ";
            for (Integer u : g.get(v)) {
                s += u+" ";
            }
            s+="\n";
        }
        return s;
    }

    public void t(boolean b, TreeMap<Integer, TreeSet<Integer>> g) {
        assertEquals("Vastasit väärin seuraavalle verkolle:\n"+descr(g),
                b,
                Hamilton.hamilton(g));
    }

    TreeMap<Integer, TreeSet<Integer>> mkT(int n) {
       TreeMap<Integer, TreeSet<Integer>> g = new TreeMap<Integer, TreeSet<Integer>>();

        for (int i = 0; i<n; i++) {
            g.put(i,new TreeSet<Integer>());
        }

        mkC(g);
        addR(g);
        return g;
    }

    void mkC(TreeMap<Integer, TreeSet<Integer>> g) {
        ArrayList<Integer> ids = new ArrayList<Integer>(g.keySet());
        int n = ids.size();
        for (int i = 0; i<ids.size(); i++) {
            int id = ids.get(i);

            TreeSet<Integer> s = new TreeSet<Integer>();
            s.add(ids.get((i+1)%n));
            s.add(ids.get((i+n-1)%n));
            g.put(id,s);
        }
    }

    void addR(TreeMap<Integer, TreeSet<Integer>> g) {
        ArrayList<Integer> ids = new ArrayList<Integer>(g.keySet());
        int n = ids.size();
        int k = n/3;
        while (k-->0) {
            int a = ids.get(r.nextInt(n));
            int b = ids.get(r.nextInt(n));
            g.get(a).add(b);
            g.get(b).add(a);
        }
    }

    TreeMap<Integer, TreeSet<Integer>> mkF1(int n) {

        int x = r.nextInt(n);

        TreeMap<Integer, TreeSet<Integer>> g = new TreeMap<Integer, TreeSet<Integer>>();

        for (int i = 0; i<n; i++) {
            if (i!=x) g.put(i,new TreeSet<Integer>());
        }

        mkC(g);
        addR(g);

        int y;
        do { y = r.nextInt(n); } while (y==x);
        g.put(x,new TreeSet<Integer>());

        g.get(x).add(y);
        g.get(y).add(x);

        return g;

    }

    TreeMap<Integer, TreeSet<Integer>> mkF2(int n) {
        int k = r.nextInt(n-2)+2;
        TreeMap<Integer, TreeSet<Integer>> g1 = new TreeMap<Integer, TreeSet<Integer>>();
        TreeMap<Integer, TreeSet<Integer>> g2 = new TreeMap<Integer, TreeSet<Integer>>();

        g1.put(k, new TreeSet<Integer>());
        g2.put(k, new TreeSet<Integer>());

        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i<=n; i++) {
            if (i!=k)
                ids.add(i);
        }
        Collections.shuffle(ids);

        int s = r.nextInt(n-3)+3;
        for (int i = 0; i<s; i++) {
            g1.put(ids.get(i), new TreeSet<Integer>());
        }
        for (int i = s; i<n; i++) {
            g2.put(ids.get(i), new TreeSet<Integer>());
        }

        mkC(g1);
        addR(g1);

        mkC(g2);
        addR(g2);

        /*System.out.println("g1 "+g1);
        System.out.println("g2 "+g2);*/

        g2.get(k).addAll(g1.get(k));
        g1.putAll(g2);

        return g1;
    }

    TreeMap<Integer, TreeSet<Integer>> mkF3(int n) {

        TreeMap<Integer, TreeSet<Integer>> g = new TreeMap<Integer, TreeSet<Integer>>();

        for (int i = 0; i<n; i++) {
            g.put(i, new TreeSet<Integer>());
        }

        ArrayList<Integer> s = new ArrayList<Integer>();
        while (s.size()<3) {
            s.add(r.nextInt(n));
        }

        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<Integer> b = new ArrayList<Integer>();

        for (int i = 0; i<n; i++) {
            boolean bb = r.nextBoolean();
            ArrayList<Integer> ab = bb ? a : b;
            if (ab.size() > 0) {
                int n0 = ab.get(r.nextInt(ab.size()));
                g.get(n0).add(i);
                g.get(i).add(n0);
            }
            ab.add(i);

            for (Integer n1 : s) {
                g.get(n1).add(i);
                g.get(i).add(n1);
            }

        }

        return g;

    }

    @Test public void testaaPienia() {

    }

    @Test public void testaaSatunnaisia() {
        int N=40;
        while (N-->0) {
            int n = 5+r.nextInt(6);
            TreeMap<Integer, TreeSet<Integer>> t = mkT(n);
            t(true, t);
            TreeMap<Integer, TreeSet<Integer>> f1 = mkF1(n);
            t(false, f1);
            TreeMap<Integer, TreeSet<Integer>> f2 = mkF2(n);
            t(false, f2);
            TreeMap<Integer, TreeSet<Integer>> f3 = mkF3(n);
            t(false, f2);
        }
    }

}
