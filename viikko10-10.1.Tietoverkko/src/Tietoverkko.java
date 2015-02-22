import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
public class Tietoverkko {

    public static void tietoverkkorec(int y, boolean[] kaydyt, boolean[][] verkko) {
        kaydyt[y] = true;
        for (int x = 0; x < verkko[y].length; x++) {
            if (verkko[y][x] == true) {
                kaydyt[y] = true;
                verkko[y][x] = false;
                verkko[x][y] = false;
                tietoverkkorec(x, kaydyt, verkko);
            }
        }
    }
    
    
    public static boolean tietoverkko(boolean[][] verkko) {
        
        boolean[][] kopio = new boolean[verkko.length][verkko.length];
        for (int i = 0; i < verkko.length; i++) {
            kopio[i] = Arrays.copyOf(verkko[i], verkko.length);
        }
        
        for (int y = 0; y < kopio.length; y++) {
            for (int x = 0; x < kopio[y].length; x++) {
                if (kopio[y][x] == true) {
                    kopio[y][x] = false;
                    kopio[x][y] = false;
                    boolean[] kaydyt = new boolean[kopio.length];
                    System.out.println(x);
                    tietoverkkorec(x,  kaydyt, kopio);
                    
                    for (boolean alkio : kaydyt) {
                        System.out.println(alkio);
                        if (alkio == false) return false;
                    }
                    
                    System.out.println("Returning true");
                    return true;
                }
            }
        }
        return false;
    }


    static boolean[][] esim1 = new boolean[][]
    {
        {false, true,  true,  true,  false},
        {true,  false, true,  true,  true},
        {true,  true,  false, false, false},
        {true,  true,  false, false, true},
        {false, true,  false, true,  false}
    };

    static boolean[][] esim2 = new boolean[][]
    {
        {false, false, true,  false, false},
        {false, false, false, true,  true},
        {true,  false, false, false, false},
        {false, true,  false, false, true},
        {false, true,  false, true,  false}
    };

    public static void main(String[] args) {
        System.out.println(tietoverkko(esim1));
        System.out.println(tietoverkko(esim2));
    }

}
