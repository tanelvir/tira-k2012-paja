
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Euler {
    public static List<Integer> eulerinKierros(boolean[][] mat) {
        // Toteuta minut
        return null;
    }


    static boolean[][] esim1 = new boolean[][] {
        {false, true,  false, true},
        {true,  false, true,  false},
        {false, true,  false, true},
        {true,  false, true,  false}
    };

    static boolean[][] esim2 = new boolean[][] {
        {false, true,  false, true,  false, false, false, },
        {true,  false, true,  false, false, false, false, },
        {false, true,  false, true,  true,  true,  false, },
        {true,  false, true,  false, false, false, false, },
        {false, false, true,  false, false, false, true,  },
        {false, false, true,  false, false, false, true,  },
        {false, false, false, false, true,  true,  false, },
    };

    static boolean[][] esim3 = new boolean[][] {
        {false, false, true,  true,  true,  true,  false, false, },
        {false, false, false, false, true,  true,  true,  true,  },
        {true,  false, false, true,  false, false, false, false, },
        {true,  false, true,  false, false, false, false, false, },
        {true,  true,  false, false, false, false, false, false, },
        {true,  true,  false, false, false, false, false, false, },
        {false, true,  false, false, false, false, false, true,  },
        {false, true,  false, false, false, false, true,  false, },
    };


    public static void main(String[] args) {
        System.out.println(eulerinKierros(esim1));
        System.out.println(eulerinKierros(esim2));
        System.out.println(eulerinKierros(esim3));

    }
}
