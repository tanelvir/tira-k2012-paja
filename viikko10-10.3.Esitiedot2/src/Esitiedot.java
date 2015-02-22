
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Esitiedot {

    public static ArrayList<String> jarjestys(Esitieto[] esitiedot) {
        HashSet<String> harmaat = new HashSet<String>();
        TreeSet<String> valkoiset = new TreeSet<String>();
        ArrayList<String> lista = new ArrayList<String>();
        

        for (Esitieto e : esitiedot) {
            for (Esitieto x : esitiedot) {
                if (e.esi.equals(x.kurssi)) {
                    harmaat.add(x.kurssi);
                }
                else if (!valkoiset.contains(e.esi)) {
                    valkoiset.add(e.esi);
                    lista.add(e.esi);
                }
            }
        }
        
        return lista;
    }
    
    public static ArrayList
    
    public static Esitieto[] esim1 = new Esitieto[]{
        new Esitieto("a", "b"),
        new Esitieto("b", "c"),
        new Esitieto("a", "d"),
        new Esitieto("d", "c"),
        new Esitieto("d", "b"),
        new Esitieto("c", "e")
    };
    public static Esitieto[] esim2 = new Esitieto[]{
        new Esitieto("ohpe", "ohja"),
        new Esitieto("ohja", "tira"),
        new Esitieto("tira", "lama"),
        new Esitieto("tira", "tiralabra"),
        new Esitieto("ohja", "javalabra"),
        new Esitieto("javalabra", "tiralabra"),
        new Esitieto("ohpe", "ohma"),
        new Esitieto("tiralabra", "ohtuprojekti"),
        new Esitieto("javalabra", "ohtuprojekti"),
        new Esitieto("ohma", "ohtu"),
        new Esitieto("ohtu", "ohtuprojekti"),
        new Esitieto("lama", "ohtuprojekti")
    };

    public static void main(String[] args) {
        System.out.println(jarjestys(esim1));
        System.out.println(jarjestys(esim2));
    }
}
