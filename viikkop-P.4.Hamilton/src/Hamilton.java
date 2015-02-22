import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class Hamilton {

    public static boolean hamilton(TreeMap<Integer, TreeSet<Integer>> verkko) {
        int koko = verkko.size();
        for (int s = 1; s <= koko; s++) {
            ArrayList<Integer> lista = new ArrayList<Integer>();
            TreeMap<Integer, TreeSet<Integer>> verkko2;
            TreeMap<Integer, TreeSet<Integer>> verkko3;
            verkko2 = verkko;
            verkko3 = verkko;
            int luku = verkko2.firstKey();
            for (int i = 1; i <= s; i++) {
                luku = verkko2.firstKey();
                verkko2.remove(luku);
            }

            boolean onko = kierrä(verkko3, s, lista, s, koko, 1, luku);
            if (onko) {
                return true;
            }
            System.out.println("OLTII TÄÄL");
            System.out.println("s on " + s);
            System.out.println("koko on " + verkko.size());
        }
        return false;
    }

    private static boolean kierrä(TreeMap<Integer, TreeSet<Integer>> verkko2, int s, ArrayList<Integer> lista, int alku, int koko, int kertoja, int luku) {
        while (!verkko2.isEmpty()) {
            System.out.println("s on " + s);
            System.out.println("luku on " + luku);
            System.out.println("alku on " + alku);
            System.out.println("kohta on " + kertoja);
            System.out.println("koko on " + koko);
            System.out.println(lista);
            if (luku == alku && kertoja == koko) {
                return true;
            }
            if (lista.contains(luku)) {
                return false;
            }
            lista.add(luku);
            if (!verkko2.isEmpty()) {
                return kierrä(verkko2, s, lista, alku, koko, kertoja + 1, luku);
            }
        }
        return false;
    }

    public static void main(String[] args) {

        TreeMap<Integer, TreeSet<Integer>> esim1 = new TreeMap<Integer, TreeSet<Integer>>();

        esim1.put(1, new TreeSet<Integer>());
        esim1.get(1).add(2);
        esim1.get(1).add(3);

        esim1.put(2, new TreeSet<Integer>());
        esim1.get(2).add(1);
        esim1.get(2).add(4);

        esim1.put(3, new TreeSet<Integer>());
        esim1.get(3).add(1);
        esim1.get(3).add(4);

        esim1.put(4, new TreeSet<Integer>());
        esim1.get(4).add(2);
        esim1.get(4).add(3);

        System.out.println(hamilton(esim1));

        TreeMap<Integer, TreeSet<Integer>> esim2 = new TreeMap<Integer, TreeSet<Integer>>();

        esim2.put(1, new TreeSet<Integer>());
        esim2.get(1).add(2);
        esim2.get(1).add(3);
        esim2.get(1).add(4);

        esim2.put(2, new TreeSet<Integer>());
        esim2.get(2).add(1);

        esim2.put(3, new TreeSet<Integer>());
        esim2.get(3).add(1);
        esim2.get(3).add(4);

        esim2.put(4, new TreeSet<Integer>());
        esim2.get(4).add(1);
        esim2.get(4).add(3);

        System.out.println(hamilton(esim2));

    }
}