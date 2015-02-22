/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
public class Potilas<Potilas> implements Comparable {

    public String nimi;
    public int tarve;

    public Potilas(String name, int necessity) {
        nimi = name;
        tarve = necessity;
    }

    @Override
    public String toString() {
        return nimi + "," + tarve;
    }

    @Override
    public int compareTo(Object o) {
       o = 
    }
}
    