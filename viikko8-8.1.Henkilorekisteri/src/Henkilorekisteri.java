
import java.util.ArrayList;
import java.util.Collections;

public class Henkilorekisteri {

    ArrayList<Henkilo> lista;

    // Toteuta tämä luokka
    public Henkilorekisteri() {
        lista = new ArrayList<Henkilo>();
    }

    public void lisaa(String etu, String suku, int ika) {
        Henkilo joku = new Henkilo(etu, suku, ika);
        lista.add(joku);
    }

    public boolean kysele(String etu, String suku, int ika) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getEtunimi().equals(etu) && lista.get(i).getSukunimi().equals(suku) && lista.get(i).getIka() == ika) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> haeKaikki() {
        ArrayList<String> tama = new ArrayList<String>();
        Collections.sort(lista);
        for (int i = 0; i < lista.size(); i++) {
            tama.add(""+lista.get(i));
        }
        return tama;
    }
}