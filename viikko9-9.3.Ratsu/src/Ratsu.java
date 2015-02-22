import java.util.ArrayDeque;

public class Ratsu {

    public static int ratsu(int x0, int y0, int x1, int y1) {
        ArrayDeque<int[]> lista = new ArrayDeque<int[]>();
        int[] taulu = {x0, y0, 0};
        lista.add(taulu);
        while (true) {
            int[] kT = lista.poll();
            if (kT[0] == x1 && kT[1] == y1) {
                return kT[2];
            }
            liiku(kT[1], kT[0], lista, y1, x1, kT[2]);
        }
    }

    public static void liiku(int y, int x, ArrayDeque taulukot, int yE, int xE, int kierros) {
        int[] a = {x + 2, y - 1, kierros + 1};
        if (check(a[0], a[1])) {
            taulukot.add(a);
        }
        int[] b = {x + 2, y + 1, kierros + 1};
        if (check(b[0], b[1])) {
            taulukot.add(b);
        }
        int[] c = {x - 2, y - 1, kierros + 1};
        if (check(c[0], c[1])) {
            taulukot.add(c);
        }
        int[] d = {x - 2, y + 1, kierros + 1};
        if (check(d[0], d[1])) {
            taulukot.add(d);
        }
        int[] e = {x + 1, y + 2, kierros + 1};
        if (check(e[0], e[1])) {
            taulukot.add(e);
        }
        int[] f = {x - 1, y + 2, kierros + 1};
        if (check(f[0], f[1])) {
            taulukot.add(f);
        }
        int[] g = {x + 1, y - 2, kierros + 1};
        if (check(g[0], g[1])) {
            taulukot.add(g);
        }
        int[] h = {x - 1, y - 2, kierros + 1};
        if (check(h[0], h[1])) {
            taulukot.add(h);
        }
    }

    public static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    public static void main(String[] args) {
        System.out.println("ratsu(0,0,1,2)=" + ratsu(0, 0, 1, 2));
        System.out.println("ratsu(0,0,7,7)=" + ratsu(0, 0, 7, 7));
        System.out.println("ratsu(2,1,0,0)=" + ratsu(2, 1, 0, 0));
    }
}