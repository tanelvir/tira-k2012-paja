/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
public class Henkilo implements Comparable<Henkilo> {

    String etunimi;
    String sukunimi;
    int ika;

    public Henkilo(String nimi1, String nimi2, int ikaa) {
        etunimi = nimi1;
        sukunimi = nimi2;
        ika = ikaa;
    }
    
    public String getEtunimi() {
        return etunimi;
    }
    
    public String getSukunimi() {
        return sukunimi;
    }
    
    public int getIka() {
        return ika;
    }

    @Override
    public int compareTo(Henkilo o) {
        int numero = this.sukunimi.compareTo(o.sukunimi);
        if (numero != 0) {
            return numero;
        }
        numero = this.etunimi.compareTo(o.etunimi);
        if (numero != 0) {
            return numero;
        }
        return (this.ika - o.ika);
    }

    @Override
    public String toString() {
        return etunimi + " " + sukunimi + " " + ika;
    }
}
