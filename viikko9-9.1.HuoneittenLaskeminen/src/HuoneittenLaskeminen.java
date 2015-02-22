
import java.util.LinkedList;
import java.util.ArrayList;

public class HuoneittenLaskeminen {

    static LinkedList<Object> lista;
    static ArrayList<LinkedList> taulukko = new ArrayList<LinkedList>();

    public static ArrayList<LinkedList> luoVieruslista(char[][] taulu) {
        for (int i = 0; i < taulu.length; i++) {
            for (int j = 0; j < taulu[i].length; j++) {
                lista = new LinkedList<Object>();
                if (i == 0 && j == 0) {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add('?');
                    lista.add(taulu[i][j+1]);
                    lista.add('?');
                    taulukko.add(lista);
                }
                else if (i==0 && j==taulu[i].length-1) {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add('?');
                    lista.add('?');
                    lista.add(taulu[i][j-1]);                    
                    taulukko.add(lista);
                }
                else if (i==taulu.length-1 && j==0) {
                    lista.add(taulu[i][j]);
                    lista.add('?');
                    lista.add(taulu[i-1][j]);
                    lista.add(taulu[i][j+1]); 
                    lista.add('?');
                    taulukko.add(lista);
                }
                else if (i==taulu.length-1 && j==taulu[i].length-1) {
                    lista.add(taulu[i][j]);
                    lista.add('?');
                    lista.add(taulu[i-1][j]);
                    lista.add('?');
                    lista.add(taulu[i][j-1]);                    
                    taulukko.add(lista);
                }
                else if (i==0) {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add('?');
                    lista.add(taulu[i][j+1]);
                    lista.add(taulu[i][j-1]);
                    taulukko.add(lista);
                }
                else if (i==taulu.length-1) {
                    lista.add(taulu[i][j]);
                    lista.add('?');
                    lista.add(taulu[i-1][j]);
                    lista.add(taulu[i][j+1]);
                    lista.add(taulu[i][j-1]);
                    taulukko.add(lista);
                }
                else if (j==0) {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add(taulu[i-1][j]);
                    lista.add(taulu[i][j+1]);
                    lista.add('?');                    
                    taulukko.add(lista);
                }
                else if (j==taulu[i].length-1) {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add(taulu[i-1][j]);
                    lista.add('?');
                    lista.add(taulu[i][j-1]);                    
                    taulukko.add(lista);
                }
                else {
                    lista.add(taulu[i][j]);
                    lista.add(taulu[i+1][j]);
                    lista.add(taulu[i-1][j]);
                    lista.add(taulu[i][j+1]);
                    lista.add(taulu[i][j-1]);                    
                    taulukko.add(lista);
                }
            }
        }
        return taulukko;
    }
    
    public static boolean etsiReitti(ArrayList<LinkedList> list) {
        
    }

    public static int huoneet(char[][] talo) {
        int huoneidenLkm = 0;
        Object piste = '.';
        ArrayList<LinkedList> vierus;
        vierus = luoVieruslista(talo);
        for (int i = 0; i < vierus.size(); i++) {
            if (vierus.get(i).getFirst()==piste) {
                for (int j = 0; j < vierus.get(i).size(); j++) {
                    if 
                }
            }
        }
    }
    static char[][] esim1 = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '#', '#', '#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
    static char[][] esim2 = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '.', '.', '.', '#', '#', '.', '#'},
        {'#', '.', '#', '.', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '#', '#', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public static void main(String[] args) {
        System.out.println(huoneet(esim1));
        System.out.println(huoneet(esim2));
    }
}