
import org.junit.Test;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import static org.junit.Assert.*;

@Points("10.3")
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
    
    public String descr(Esitieto[] es) {
        StringBuilder b = new StringBuilder();
        for (Esitieto e : es)
            b.append(e.esi).append(" ").append(e.kurssi).append("\n");
        return b.toString();
    }
    
    public void c(String v, HashSet<Esitieto> es, ArrayList<String> jarj) {
        for (Esitieto e : es) {
            int eka = jarj.indexOf(e.esi);
            int toka = jarj.indexOf(e.kurssi);
            assertTrue(v+"Kurssi "+e.esi+" ei ollut tuloksessasi\n"+jarj,
                    eka>=0);
            assertTrue(v+"Kurssi "+e.kurssi+" ei ollut tuloksessasi\n"+jarj,
                    toka>=0);
            assertTrue(v+"Kurssit "+e.esi+" ja "+e.kurssi+" olivat väärässä järjestyksessä tuloksessasi\n"+jarj,
                    eka<toka);
        }
    }
    
    public void t(HashSet<Esitieto> es) {
        
        Esitieto[] input = new Esitieto[es.size()];
        int ind = 0;
        for (Esitieto e : es)
            input[ind++] = e(e.esi,e.kurssi);
            
        c("Vastasit väärin syötteelle:\n"+descr(input)+"\n",
                es,
                Esitiedot.jarjestys(input));
    }
    
    @Test
    public void testaaPienia() {
        t(mkU(3,1,1));
        t(mkU(4,2,1));
    }
    
    @Test
    public void testaaSatunnaisia() {
        for (int siz = 5; siz<=30; siz+=5) {
            for (int deg = 1; deg <= 10; deg+=3) {
                int comp = r.nextInt(Math.min(6,siz/4))+1;
                t(mkU(siz,deg,comp));
            }
        }
    }
    
}
