import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Esitiedot {


    public static boolean sykli(Esitieto[] esitiedot) {
        
        // Toteuta minut
        HashSet<String> harmaat = new HashSet<String>();
        TreeSet<String> valkoiset = new TreeSet<String>();

        for (Esitieto e : esitiedot) {
            valkoiset.add(e.kurssi);
            valkoiset.add(e.esi);
        }

        while (valkoiset.size() != 0) {
            if (läpikäynti(esitiedot, harmaat, valkoiset, valkoiset.pollFirst())) {
                return true;
            }
        }

        return false;
    }
        public static boolean läpikäynti(Esitieto[] esitiedot, Set<String> harmaat, Set<String> valkoiset, String kurssi) {

        if (harmaat.contains(kurssi)) {
            return true;
        }
        harmaat.add(kurssi);
        valkoiset.remove(kurssi);

        for (Esitieto e : esitiedot) {
            if (e.esi.equals(kurssi)) {
                //miltei saatu sykli aikaan
                if (läpikäynti(esitiedot, harmaat, valkoiset, e.kurssi)) {
                    return true;
                }
            }
        }

        harmaat.remove(kurssi);
        return false;
    }


    public static Esitieto[] esim1 = new Esitieto[]{
        new Esitieto("a", "b"),
        new Esitieto("b", "c"),
        new Esitieto("a", "d"),
        new Esitieto("d", "c"),
        new Esitieto("d", "b"),
        new Esitieto("c", "e")
    };
    public static Esitieto[] esim2 = new Esitieto[]{
        new Esitieto("a", "b"),
        new Esitieto("b", "c"),
        new Esitieto("a", "d"),
        new Esitieto("d", "c"),
        new Esitieto("d", "b"),
        new Esitieto("c", "a")
    };

    public static void main(String[] args) {
        System.out.println(sykli(esim1));
        System.out.println(sykli(esim2));
    }

}