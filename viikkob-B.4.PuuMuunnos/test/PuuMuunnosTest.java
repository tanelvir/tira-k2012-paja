import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Random;
import java.util.ArrayList;

@Points("B.4")
public class PuuMuunnosTest {

    public static Node n(int key) {
        return new Node(key);
    }

    public static Node n(int key, Node left, Node right) {
        return new Node(key, left, right);
    }

    public static boolean eq(Node n, Node m) {
        if (n==null && m==null) {
            return true;
        }
        if (n==null || m==null) {
            return false;
        }
        if (n.getKey() != m.getKey()) {
            return false;
        }
        return eq(n.getLeft(),m.getLeft()) && eq(n.getRight(),m.getRight());
    }

    public static int h(Node n) {
        if (n==null) {
            return -1;
        }
        int hl = h(n.getLeft());
        int hr = h(n.getRight());
        return Math.max(hl,hr)+1;
    }

    public static boolean onlt(Node n) {
        if (n==null) {
            return true;
        }
        int hl = h(n.getLeft());
        int hr = h(n.getRight());
        return (hl==hr || hl==hr+1) && onlt(n.getLeft()) && onlt(n.getRight());
    }

    public static ArrayList<Integer> ej(Node n) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        ej(a, n);
        return a;
    }

    public static void ej(ArrayList<Integer> a, Node n) {
        if (n==null)
            return;
        a.add(n.getKey());
        ej(a,n.getLeft());
        ej(a,n.getRight());
    }

    public static Node[] simples() {
        return new Node[]
            {n(1),
             n(2,null,n(3)),
             n(1,n(7),n(8)),
             n(1,n(7),n(8,null,n(9))),
             n(2,null,n(3,n(4),null)),
             new Node(8,
                      new Node(3,
                               new Node(2,
                                        new Node(1),
                                        null),
                               new Node(6,
                                        new Node(5),
                                        new Node(7))),
                      new Node(9,
                               null,
                               new Node(10)))

            };
    }

    public void t(Node n) {
        String s = n.toString();
        ArrayList<Integer> ne = ej(n);
        Node m = PuuMuunnos.muunna(n);
        ArrayList<Integer> me = ej(m);
        assertTrue("Et palauttanut lähes täydellistä puuta. Syöte:\n"+s+"\nTulos:\n"+m+"\n",
                   onlt(m));
        assertEquals("Palauttamasi puun esijärjestys oli väärä. Syöte:\n"+s+"\nTulos:\n"+m+"\n",
                     ne,
                     me);
    }

    @Test
    public void testaaPienia() {
     
        for (Node n : simples()) {
            t(n);
        }
   
    }

    static Node random(Random r) {
        int x = r.nextInt(7);
        int id = r.nextInt(1000);
        if (x<4) {
            return n(id);
        } else {
            return n(id,random(r),random(r));
        }
    }

    static Node random(int h, Random r) {
        if (h==0) {
            return random(r);
        }
        int id = r.nextInt(1000);
        return n(id,random(h-1,r),random(h-1,r));
    }

    @Test
    public void testaaSatunnaisiaLahesTaydellinen() {
        Random r = new Random();

        for (int i = 0; i<20; i++) {
            t(random(4,r));
        }

    }
}