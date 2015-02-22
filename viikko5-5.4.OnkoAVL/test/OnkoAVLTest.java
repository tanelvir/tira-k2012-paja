import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;

@Points("5.4")
public class OnkoAVLTest {

    static Tree t(Node n) {
        return new Tree(n);
    }

    static Node n() {
        return new Node(0, null);
    }

    static Node n(Node left, Node right) {
        return new Node(0, null, left, right);
    }

    @Test public void testaaPienia() {
        
        c(true,null);
        c(true,n());
        c(true,n(n(),null));
        c(false,n(n(n(),null),null));
        c(true,n(n(),
                 n(n(),
                   null)));
    }

    void c(boolean b, Node n) {
        Tree tr = t(n);
        String s = tr.toString();
        assertEquals("Puu "+(b?"on":"ei ole")+" AVL-puu:\n"+s,
                     b,
                     OnkoAVL.isAVL(tr));
    }

    static Node yes(Random r, int h) {
        if (h==-1) {
            return null;
        }
        if (h==0) {
            return n();
        }
        int diff = r.nextInt(3)-1;
        if (diff==0) {
            return n(yes(r,h-1),yes(r,h-1));
        } else if (diff==1) {
            return n(yes(r,h-1),yes(r,h-2));
        } else {
            return n(yes(r,h-2),yes(r,h-1));
        }
    }
    
    @Test public void testaaSatunnaisiaKylla() {
        Random r = new Random();
        for (int i=4;i<14;i++) {
            c(true,yes(r,i));
            c(true,yes(r,i));
        }
    }

    @Test public void testaaSatunnaisiaEi() {
        Random r = new Random();
        c(false,n(yes(r,4),
                  yes(r,2)));

        c(false,n(yes(r,7),
                  yes(r,2)));

        c(false,n(yes(r,8),
                  yes(r,6)));

        c(false,n(n(yes(r,4),
                    yes(r,4)),
                  n(yes(r,4),
                    yes(r,2))));

        c(false,n(n(yes(r,6),
                    yes(r,9)),
                  n(yes(r,6),
                    yes(r,6))));
    }

    static Node no(Random r, int h0, int h1) {
        if (h0<=2) {
            return yes(r,h1+h0);
        }
        Node a = no(r,h0-3,h1);
        Node b = no(r,h0-1,h1);
        if (r.nextInt(2)==0) {
            return n(a,b);
        } else {
            return n(b,a);
        }
    }


    @Test public void testaaSatunnaisia() {
        Random r = new Random();
        for (int i=5; i<13; i++) {
            if (r.nextBoolean())
                c(false,no(r,i-2,2));
            else
                c(true,yes(r,i));
        }

    }

}