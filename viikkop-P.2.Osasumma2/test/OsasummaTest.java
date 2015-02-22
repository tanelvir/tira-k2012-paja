
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.timing.CpuStopwatch;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

@Points("P.2")
public class OsasummaTest {


    Random r = new Random();
    int MIN=-100;
    int MAX=100;

    public int[][] r(int n, int m) {
        int[][] ret = new int[n][m];
        for (int i = 0; i<ret.length; i++) {
            for (int j = 0; j<ret[0].length; j++) {
                ret[i][j] = r.nextInt(MAX-MIN)+MIN;
            }
        }
        return ret;
    }

    @Test public void testaaOperaatiot() {
        int KRT = 5;
        int N = 5;
        int M = 4;
        for (int krt = 1; krt<KRT; krt++) {
            int n = N*krt;
            int m = M*krt;
            int[][] t = r(n,m);
            String str = Arrays.deepToString(t);
            Osasumma o = new Osasumma(t);

            for (int i = 0; i<n; i++) {
                for (int j = 0; j<m; j++) {
                    //System.out.println("DBG "+i+" "+j+" "+n+" "+m);
                    assertEquals("Taulukolle\n"+str+"\noperaatio get("+i+","+j+")\n",
                            t[i][j],
                            o.get(i,j));
                }
            }

            for (int i = 0; i<n; i++) {
                for (int j = 0; j<m; j++) {
                    for (int a = i; a<n; a++) {
                        for (int b = j; b<m; b++) {

                            int ans = 0;
                            for (int y = i; y<=a; y++) {
                                for (int x = j; x<=b; x++) {
                                    ans += t[y][x];
                                }
                            }

                            assertEquals("Taulukolle\n"+str+"\noperaatio osasumma("+i+","+j+","+a+","+"b"+")\n",
                                    ans,
                                    o.osasumma(i,j,a,b));
                        }
                    }

                }
            }
        }
    }

    @Test public void testaaAika() {
        int N = 1000;
        int M = 1000;

        Osasumma o = new Osasumma(r(N, M));

        CpuStopwatch csw = new CpuStopwatch(CpuStopwatch.Mode.USER);
        for (int i = 0; i<N; i+=20) {
            for (int j = 0; j<M; j+=21) {
                for (int a = i; a<N; a+=22) {
                    for (int b = j; b<M; b+=29) {

                        o.osasumma(i,j,a,b);
                    }
                }
            }
        }
        double ms = csw.getElapsedTime()*1000;
        double raja = 150;

        assertTrue("Käytit aikaa "+ms+"ms, joka on yli "+raja+"ms.",
                ms<=raja);



    }

}