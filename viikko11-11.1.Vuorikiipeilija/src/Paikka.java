/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
public class Paikka implements Comparable<Paikka> {

    int korkeus;

    public Paikka(int high) {
        korkeus = high;
    }

    @Override
    public int compareTo(Paikka o) {
        if (this.korkeus>o.korkeus) {
            return 1;
        }
        else if (this.korkeus<o.korkeus) {
           return -1; 
        }
        else return 0;
    }
}
