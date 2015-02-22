import java.util.HashMap;
import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("5.1")
public class HakupuuTest {

    static Tree t(Node n) {
        return new Tree(n);
    }

    static Node n(int key, Object value) {
        return new Node(key, value);
    }

    static Node n(int key, Object value, Node left, Node right) {
        return new Node(key, value, left, right);
    }

    public void r(Random r, Node n) {
        Node m = n;
        int key = m.getKey();
        Object val = m.getValue();
        while (m!=null) {
            key = m.getKey();
            val = m.getValue();
            int i = r.nextInt(5);
            if (i%3==0) {
                m=m.getLeft();
            } else if (i%3==1) {
                m=m.getRight();
            } else {
                break;
            }
        }

        z(n,key,val);

    }

    public Node rtree(Random r, int lo, int hi) {

        if (lo>=hi-1) {
            return null;
        }

        int key = lo+r.nextInt(hi-lo);
        int val = r.nextInt(100);
        Node lef = rtree(r,lo,key);
        Node rig = rtree(r,key+1,hi);
        return n(key,val,lef,rig);
    }

    public void z(Node n, int key, Object val) {
        assertEquals("Haettiin avainta "+key+" puusta "+n,
                     val,
                     Hakupuu.search(t(n),key));
    }

    @Test
    public void testaaPienia() {

        Node puu0 = n(1,"R");
        z(puu0,1,"R");
        z(puu0,2,null);

        Node puu1 = n(1,"X",
                      null,
                      n(2,"Y"));
        z(puu1,1,"X");
        z(puu1,2,"Y");
        z(puu1,3,null);

        Node puu2 = n(2,"A",
                      n(1,"F"),
                      n(3,"G"));

        z(puu2,2,"A");
        z(puu2,1,"F");
        z(puu2,3,"G");
        z(puu2,4,null);
        z(puu2,0,null);
    }


    @Test
    public void testaaSearchSatunnaisia() {
        
        Random r = new Random();

        int N=10;
        int K=10;
        int SIZE=40;

        for (int i = 0; i<N; i++) {
            Node n = rtree(r,0,SIZE);
            
            for (int k = 0; k<K; k++) {
                r(r,n);
            }
        }
    }

    public Node dtree(int secret, int lo, int hi) {

        if (lo>hi-1) {
            return null;
        }

        int key = lo+(hi-lo)/2;
        int val = secret * key;
        Node lef = dtree(secret,lo,key);
        Node rig = dtree(secret,key+1,hi);
        return n(key,val,lef,rig);
    }

    @Test
    public void testaaSearchIso() {
        Random r = new Random();
        int secret = r.nextInt(100);

        int N=3;
        int K=100;
        int SIZE=2048;

        for (int i = 0; i<N; i++) {
            Node n = dtree(secret,0,SIZE);

            long raja = 90*K;
            CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        
            for (int k = 0; k<K; k++) {
                int key = r.nextInt(SIZE);
                z(n,key,key*secret);
            }

            double aika = csw.getElapsedTime();
            long ms = (long)(aika*1000);
            assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms",
                       ms<=raja);
        }
        
    }

    public boolean eq(Node n, Node m) {
        if (n==null && m==null) {
            return true;
        }
        if (n==null || m==null) {
            return false;
        }
        if (n.getKey() != m.getKey()) {
            return false;
        }
        if (n.getValue() != m.getValue()) {
            return false;
        }
        return eq(n.getLeft(),m.getLeft()) && eq(n.getRight(),m.getRight());
    }

    public boolean eq(Tree t, Tree s) {
        return eq(t.getRoot(),s.getRoot());
    }

    public void ins(int k, Object val, Tree in, Tree should) {

        String s = in.toString();
        Hakupuu.insert(in,k,val);
        assertTrue("Kun lisättiin avain "+k+" ja arvo "+val+" puuhun\n"+s+"\nsaatiin\n"+in+"\neikä\n"+should,
                   eq(in,should));

    }

    @Test
    public void testaaInsertPienia() {

        ins(0,1,
            t(null),
            t(n(0,1)));
        ins(0,1,
            t(n(1,2)),
            t(n(1,2,
                n(0,1),
                null)));
        ins(2,3,
            t(n(1,2)),
            t(n(1,2,
                null,
                n(2,3))));
        ins(2,3,
            t(n(1,2,
                n(0,7),
                n(3,7))),
            t(n(1,2,
                n(0,7),
                n(3,7,
                  n(2,3),
                  null))));

    }

    public void h(Node n, int lo, int hi) {

        if (n==null) {
            return;
        }

        int k = n.getKey();

        assertTrue("Avain "+k+" ei ole välillä "+lo+","+hi,
                   k>=lo && k<=hi);

        h(n.getLeft(),lo,k-1);
        h(n.getRight(),k+1,hi);
        
    }

    public void h(Tree t) {

        try {
            h(t.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        } catch (AssertionError e) {
            fail("Puu\n"+t+"\nei ole hakupuu:\n"+e.getMessage());
        }

    }

    public void c(Tree t, int k) {
        assertTrue("Avainta "+k+" ei löytynyt puusta\n"+t,
                   c(t.getRoot(), k));
    }

    public boolean c(Node n, int k) {
        if (n==null) {
            return false;
        }
        return n.getKey()==k || c(n.getLeft(),k) || c(n.getRight(),k);
    }

    @Test
    public void testaaInsertMonta() {
        Tree t = t(null);
        Random r = new Random();
        int RAJA=100;

        for (int i = 0; i<100; i++) {
            int k = r.nextInt(100);
            Hakupuu.insert(t,k,i);
            c(t, k);
            h(t);
        }
    }

    @Test
    public void testaaInsertJaSearch() {
        int MAX = 4096;
        int N = 4096;
        long raja = 200;
        Random r = new Random();

        Tree t = new Tree();
        HashMap hm = new HashMap();
        
        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int i = 0; i<N; i++) {
            int key = r.nextInt(MAX);
            hm.put(key, i);
            Hakupuu.insert(t, key, i);
        }
        for (int i = 0; i<N; i++) {
            int key2 = r.nextInt(MAX);
            assertEquals("Palautit väärän arvon metodista search kun tehtiin monta lisäystä ja kyselyä.",
                         (Integer) hm.get(key2),
                         (Integer) Hakupuu.search(t,key2));
        }
        double aika = csw.getElapsedTime();
        long ms = (long)(aika*1000);
        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms",
                   ms<=raja);
    }

}