
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("P.3")
public class VerkonKuvausTest {

    static class N {
        public int id;
        public HashSet<N> n = new HashSet<N>();
    }

    Random r = new Random();

    public String descr(HashMap<Integer,HashSet<Integer>> g) {
        TreeSet<Integer> ts = new TreeSet<Integer>(g.keySet());
        String s = "";
        for (Integer v : ts) {
            s += v +": ";
            TreeSet<Integer> ns = new TreeSet<Integer>(g.get(v));
            for (Integer u : ns) {
                s += u+" ";
            }
            s+="\n";
        }
        return s;
    }

    public ArrayList<N> mk(int n) {

        ArrayList<Integer> idss = new ArrayList<Integer>();
        for (int i = 1; i<=n; i++) {
            idss.add(i);
        }
        Collections.shuffle(idss);

        ArrayList<N> al = new ArrayList<N>();
        for (int i = 0; i<n; i++) {
            N nod = new N();
            nod.id=idss.remove(0);

            al.add(nod);
            if (i>0) {
                int j = r.nextInt(i);
                al.get(j).n.add(nod);
                nod.n.add(al.get(j));
            }
        }
        return al;
    }

    public ArrayList<Integer> kuvaus(ArrayList<N> al) {
        ArrayList<Integer> k = new ArrayList<Integer> ();
        while (al.size()>1) {
            N pienin = null;

            for (N n : al) {
                if (n.n.size() == 1 && (pienin == null || n.id < pienin.id)) {
                    pienin = n;
                }
            }
            assert(pienin != null);

            al.remove(pienin);
            for (N n : al) {
                n.n.remove(pienin);
            }
            for (N n : pienin.n) {
                k.add(n.id);
            }
        }
        //k.add(al.get(0).id);
        return k;
    }

    public HashMap<Integer,HashSet<Integer>> list(ArrayList<N> al) {
        HashMap<Integer,HashSet<Integer>> hm = new HashMap<Integer,HashSet<Integer>>();
        for (N n : al) {
            HashSet<Integer> hs = new HashSet<Integer>();
            for (N m : n.n) {
                hs.add(m.id);
            }
            hm.put(n.id, hs);
        }
        return hm;
    }

    public void t(ArrayList<N> al) {
        HashMap<Integer,HashSet<Integer>> l = list(al);
        String s1 = descr(l);
        ArrayList<Integer> k = kuvaus(al);
        String sk = ""+k;
        HashMap<Integer,HashSet<Integer>> l2 = VerkonKuvaus.kuvaus(k);
        String s2 = descr(l2);

        String virhe = "Syöte: "+sk+"\nOdotettu:\n"+s1+"Palautettu:\n"+s2;

        assertEquals(virhe,
                l.keySet(),
                l2.keySet());

        for (Integer i : l.keySet()) {
            assertEquals(virhe+"\nSolmu "+i+"\n",
                    l.get(i),
                    l2.get(i));
        }
    }

    @Test public void testaaEsimerkit() {
        ArrayList<N> esim1 = new ArrayList<N>();

        N n1 = new N();
        n1.id = 1;
        N n2 = new N();
        n2.id = 2;
        N n3 = new N();
        n3.id = 3;
        N n4 = new N();
        n4.id = 4;
        N n5 = new N();
        n5.id = 5;

        Collections.addAll(n1.n, n2, n3, n4);
        Collections.addAll(n2.n, n1);
        Collections.addAll(n3.n, n1);
        Collections.addAll(n4.n, n1, n5);
        Collections.addAll(n5.n, n4);

        Collections.addAll(esim1,n1,n2,n3,n4,n5);

        t(esim1);

        ArrayList<N> esim2 = new ArrayList<N>();

        N m1 = new N();
        m1.id = 1;
        N m2 = new N();
        m2.id = 2;
        N m3 = new N();
        m3.id = 3;
        N m4 = new N();
        m4.id = 4;
        N m5 = new N();
        m5.id = 5;
        N m6 = new N();
        m6.id = 6;
        N m7 = new N();
        m7.id = 7;


        Collections.addAll(m1.n, m2, m3);
        Collections.addAll(m2.n, m1);
        Collections.addAll(m3.n, m1, m4, m5);
        Collections.addAll(m4.n, m3, m6, m7);
        Collections.addAll(m5.n, m3);
        Collections.addAll(m6.n, m4);
        Collections.addAll(m7.n, m4);

        Collections.addAll(esim2,m1,m2,m3,m4,m5,m6,m7);

        t(esim2);
    }

    @Test public void testaaSatunnaisia() {
        for (int i = 3; i<=50; i++) {
            t(mk(i));
        }
    }

}
