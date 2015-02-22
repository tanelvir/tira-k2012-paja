
import org.junit.Test;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import static org.junit.Assert.*;

@Points("10.2")
public class EsitietoTest {
    
    static Esitieto e(String a, String b) {
        return new Esitieto(a,b);
    }
    
    Random r = new Random();
    
    String kurssi() {
        return "k"+Integer.toString(r.nextInt(), Character.MAX_RADIX);
    }
    
    HashSet<Esitieto> mkU(int n, int deg) {
        HashSet<Esitieto> es = new HashSet<Esitieto>();
        ArrayList<String> ks = new ArrayList<String>();
        for (int i = 0; i<n; i++)
            ks.add(kurssi());
        for (int i = 0; i<n-1; i++) {
            int d = r.nextInt(deg);
            es.add(e(ks.get(i),ks.get(i+1)));
            for (int j = 0; j<d; j++) {
                int k = r.nextInt(n-i-1)+i+1;
                es.add(e(ks.get(i),ks.get(k)));
            }
        }
        return es;
    }
    
    HashSet<Esitieto> mkC(int n, int deg) {
        HashSet<Esitieto> es = new HashSet<Esitieto>();
        ArrayList<String> ks = new ArrayList<String>();
        for (int i = 0; i<n; i++)
            ks.add(kurssi());
        
        for (int i = 0; i<n-1; i++) {
            int d = r.nextInt(deg);
            es.add(e(ks.get(i),ks.get(i+1)));
            for (int j = 0; j<d; j++) {
                int k = r.nextInt(n-i-1)+i+1;
                es.add(e(ks.get(i),ks.get(k)));
            }
        }
        int a = r.nextInt(ks.size()-1)+1;
        int b = r.nextInt(a);
        es.add(e(ks.get(a),ks.get(b)));
        return es;
    }
    
    HashSet<Esitieto> mkU(int n, int deg, int comp) {
        HashSet<Esitieto> es = new HashSet<Esitieto>();
        int left = n;
        while (left > 0) {
            int m = n/comp;
            left-=m;
            es.addAll(mkU(m,deg));
        }
        return es;
    }
    
    HashSet<Esitieto> mkC(int n, int deg, int comp) {
        HashSet<Esitieto> es = new HashSet<Esitieto>();
        boolean b = true;
        int left = n;
        while (left > 0) {
            int m = n/comp;
            left-=m;
            if (b) {
                b = false;
                es.addAll(mkC(m,deg));
            } else 
                es.addAll(mkU(m,deg));
        }
        return es;
    }
    
    public String descr(Esitieto[] es) {
        StringBuilder b = new StringBuilder();
        for (Esitieto e : es)
            b.append(e.esi).append(" ").append(e.kurssi).append("\n");
        return b.toString();
    }
    
    public void t(boolean b, HashSet<Esitieto> es) {
        
        Esitieto[] input = new Esitieto[es.size()];
        int ind = 0;
        for (Esitieto e : es)
            input[ind++] = e;
            
        assertEquals("Vastasit väärin syötteelle:\n"+descr(input)+"\n",
                b,
                Esitiedot.sykli(input));
    }
    
    @Test
    public void testaaPienia() {
        t(false,mkU(3,1,1));
        t(true, mkC(3,1,1));
        t(false,mkU(4,2,1));
        t(true, mkC(4,2,1));
    }
    
    @Test
    public void testaaSatunnaisia() {
        for (int siz = 5; siz<=30; siz+=5) {
            for (int deg = 1; deg <= 10; deg+=3) {
                int comp = r.nextInt(Math.min(6,siz/4))+1;
                t(false, mkU(siz,deg,comp));
                t(true,  mkC(siz,deg,comp));
            }
        }
    }
    
}
