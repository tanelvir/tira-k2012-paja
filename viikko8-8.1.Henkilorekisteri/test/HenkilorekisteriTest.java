import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;

@Points("8.1")
public class HenkilorekisteriTest {

    String r(Random r) {
        int N = 10;
        String s = "";
        while (N-->0) {
            int i = r.nextInt(20);
            s+=Character.forDigit(i,20);
        }
        return s;
    }

    @Test
    public void testaaLisaysJaKysely() {

        int N = 30;
        Random r = new Random();
        String[] etu = new String[N];
        String[] suku = new String[N];
        int[] ika = new int[N];

        Henkilorekisteri h = new Henkilorekisteri();

        for (int i = 0; i<N; i++) {
            etu[i] = r(r);
            suku[i] = r(r);
            ika[i] = r.nextInt(100);
            h.lisaa(etu[i],suku[i],ika[i]);
        }

        for (int i = 0; i<N; i++) {
            assertTrue("Rekisteriin lisättyä henkilöä ei löytynyt!", h.kysele(etu[i],suku[i],ika[i]));
            assertFalse("Rekisteriin lisäämätön henkilö ei löytyi!", h.kysele("X"+etu[i],suku[i],ika[i]));
            assertFalse("Rekisteriin lisäämätön henkilö ei löytyi!", h.kysele(etu[i],"X"+suku[i],ika[i]));
            assertFalse("Rekisteriin lisäämätön henkilö ei löytyi!", h.kysele(etu[i],suku[i],100+ika[i]));
        }
    }
    
    @Test
    public void testaaJarjestys() {
        int P = 5;

        Random r = new Random();

        String[] etu = new String[P];
        for (int i = 0; i<P; i++) {
            etu[i] = r(r);
        }
        Arrays.sort(etu);

        String[] suku = new String[P];
        for (int i = 0; i<P; i++) {
            suku[i] = r(r);
        }
        Arrays.sort(suku);

        int[] iat = new int[3];
        for (int i = 0; i<3; i++) {
            iat[i] = r.nextInt(17);
        }
        Arrays.sort(iat);

        ArrayList<Integer> ind = new ArrayList<Integer>();
        for (int i = 0; i<P*P; i++) {
            ind.add(i);
        }
        Collections.shuffle(ind);

        Henkilorekisteri h = new Henkilorekisteri();

        for (int i : ind) {
            h.lisaa(etu[i%P],suku[i/P],iat[0]);
            h.lisaa(etu[i%P],suku[i/P],iat[1]);
            h.lisaa(etu[i%P],suku[i/P],iat[2]);
        }

        ArrayList<String> ulos = h.haeKaikki();

        ArrayList<String> exp = new ArrayList<String>();

        for (String s : suku) {
            for (String e : etu) {
                for (int ika : iat) {
                    exp.add(e+" "+s+" "+ika);
                }
            }
        }

        assertEquals("haeKaikki() palautti väärän määrän nimiä!",
                     exp.size(),
                     ulos.size());

        for (int i = 0; i<exp.size(); i++) {
            assertEquals("Väärä nimi tai nimien järjestys metodin haeKaikki() palautuksessa!\n"+
                         "Palautit: "+ulos+"\n"+
                         "Odotettiin: "+exp+"\n",
                         exp.get(i),
                         ulos.get(i));
        }
    }

}
