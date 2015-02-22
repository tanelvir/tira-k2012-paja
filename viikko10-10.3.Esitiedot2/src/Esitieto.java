/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author opqdonut
 */
class Esitieto {
    public final String kurssi;
    public final String esi;

    Esitieto(String esi, String kurssi) {
        this.esi = esi;
        this.kurssi = kurssi;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Esitieto other = (Esitieto) obj;
        if ((this.kurssi == null) ? (other.kurssi != null) : !this.kurssi.equals(other.kurssi)) {
            return false;
        }
        if ((this.esi == null) ? (other.esi != null) : !this.esi.equals(other.esi)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.kurssi != null ? this.kurssi.hashCode() : 0);
        hash = 83 * hash + (this.esi != null ? this.esi.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Esitieto{" + "kurssi=" + kurssi + ", esi=" + esi + '}';
    }
    
}
