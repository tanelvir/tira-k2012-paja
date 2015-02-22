import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;

@Points("5.5")
public class JalkijarjestysTest {

    static class N {
        int val;
        N l;
        N r;

        public N(int val, N l, N r) {
            this.val=val;
            this.l=l;
            this.r=r;
        }
    }

    static N n(int val, N l, N r) {
        return new N(val,l,r);
    }

    static N n(int val) {
        return new N(val,null,null);
    }

    static int counter = 0;
    static N m() {
        return n(counter++);
    }
    static N m(N l, N r) {
        return n(counter++, l, r);
    }

    static ArrayList<Integer> esi(ArrayList<Integer> al, N n) {
        al.add(n.val);
        if (n.l !=null)
            esi(al, n.l);
        if (n.r!=null)
            esi(al, n.r);
        return al;
    }

    static ArrayList<Integer> sisa(ArrayList<Integer> al, N n) {
        if (n.l!=null)
            sisa(al, n.l);
        al.add(n.val);
        if (n.r!=null)
            sisa(al, n.r);
        return al;
    }

    static ArrayList<Integer> jalki(ArrayList<Integer> al, N n) {
        if (n.l!=null)
            jalki(al, n.l);
        if (n.r!=null)
            jalki(al, n.r);
        al.add(n.val);
        return al;
    }

    static int[] conv(ArrayList<Integer> al) {
        int[] ret = new int[al.size()];
        int i = 0;
        for (int x : al) {
            ret[i++]=x;
        }
        return ret;
    }

    static void t(N n) {
        int[] esi = conv(esi(new ArrayList<Integer>(), n));
        int[] sisa = conv(sisa(new ArrayList<Integer>(), n));
        int[] jalki = conv(jalki(new ArrayList<Integer>(), n));

        String sesi = Arrays.toString(esi);
        String ssisa = Arrays.toString(sisa);

        int[] vastaus = Jalkijarjestys.jalkijarjestys(esi, sisa);

        assertArrayEquals("Vastasit väärin kun\nesi = "+sesi+"\nsisa = "+ssisa
                          +"\njalki = "+Arrays.toString(jalki)
                          +"\nVastauksesi oli:\n"+Arrays.toString(vastaus),
                          jalki,
                          vastaus);
    }

    @Test
    public void testaaPienia() {

        t(m());
        t(m());
        t(m(m(),null));
        t(m(m(),m()));
        t(m(m(),m(m(),m())));

    }

    static N perfect(int n) {
        if (n==-1) {
            return null;
        }

        return m(perfect(n-1),perfect(n-1));
    }

    static N random(Random r, ArrayList<Integer> ids) {
        int x = r.nextInt(7);

        int id = ids.get(0);
        ids.remove(0);
        if (x<4) {
            return n(id);
        } else {
            return n(id,random(r,ids),random(r,ids));
        }
    }

    static N random(int h, Random r, ArrayList<Integer> ids) {
        if (h==0) {
            return random(r, ids);
        }
        int id = ids.get(0);
        ids.remove(0);
        return n(id,random(h-1,r,ids),random(h-1,r,ids));
    }
    
    @Test
    public void testaaSuuria() {

        Random r = new Random();

        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (int i=0; i<100000; i++) {
            ids.add(i);
        }
        
        Collections.shuffle(ids, r);

        t(perfect(7));
        t(perfect(8));
        t(perfect(9));

        for (int i = 0; i<20; i++) {
            t(random(4,r,ids));
        }

    }

}