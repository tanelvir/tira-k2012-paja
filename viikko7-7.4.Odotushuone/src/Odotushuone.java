
import java.util.PriorityQueue;

public class Odotushuone {
    // Toteuta tämä luokka!

    public PriorityQueue potilaat;
    public Potilas henkilo;

    public Odotushuone() {
        potilaat = new PriorityQueue();
    }

    public void lisaa(String nimi, int tarve) {
        if (tarve > 1000000) {
            tarve = 1000000;
        }
        if (tarve < 1) {
            tarve = 1;
        }
        henkilo = new Potilas(nimi, tarve);
        potilaat.add(henkilo);
    }

    public String seuraavaPotilas() {
        if (!potilaat.isEmpty()) {
            return "" + potilaat.poll();
        } else {
            return null;
        }
    }
}
