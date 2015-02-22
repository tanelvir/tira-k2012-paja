
import java.util.ArrayList;

public class HuoneittenKoot {

    static int laskuri;

    public static ArrayList<Integer> koot(char[][] talo) {
        // TOTEUTA MINUT
        return null;
    }


    static char[][] esim1 = new char[][] {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    static char[][] esim2 = new char[][] {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '.', '.', '#', '.', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '.', '#', '.', '#'},
        {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public static void main(String[] args) {
        System.out.println(koot(esim1));
        System.out.println(koot(esim2));
    }

}

