
public class Vankilapako {

    public static int[] etsiPiste(char[][] prison) {
        int[] taulu = new int[2];
        for (int i = 0; i < prison.length; i++) {
            for (int j = 0; j < prison[i].length; j++) {
                if (prison[i][j] == 'X') {
                    taulu[0] = i;
                    taulu[1] = j;
                }
            }
        }
        return taulu;
    }
    
    
    public static char[][] etene(char[][] taulu, int[] piste) {
        int ta1 = piste[0];
        int ta2 = piste[1];
        while (true) {
            if (taulu[ta1][ta2+1]=='.' && taulu[ta1][ta2+1]!='K') {
               taulu[ta1][ta2] = taulu[ta1][ta2+1];
               taulu[ta1][ta2] = 'K';
            }
            else if (taulu[ta1][ta2-1]=='.' && taulu[ta1][ta2-1]!='K') {
               taulu[ta1][ta2] = taulu[ta1][ta2-1];
               taulu[ta1][ta2] = 'K';
            }
            else if (taulu[ta1+1][ta2]=='.' && taulu[ta1+1][ta2]!='K') {
               taulu[ta1][ta2] = taulu[ta1+1][ta2];
               taulu[ta1][ta2] = 'K';
            }
            else if (taulu[ta1-1][ta2]=='.' && taulu[ta1-1][ta2]!='K') {
               taulu[ta1][ta2] = taulu[ta1-1][ta2];
               taulu[ta1][ta2] = 'K';
            }
            else if (taulu[ta1][ta2]=='#') {
                break;
            }
            
            System.out.println(taulu);
        }
        return taulu;
        
    }

    public static char[] pakene(char[][] vankila) {
        int taulu[] = etsiPiste(vankila);
        char kaulu[][] = etene(vankila, taulu);
        return kaulu[0];
    }
    static char[][] v1 = new char[][]{{'#', '.', '.', '#', '#'},
        {'#', '.', '#', '.', '#'},
        {'#', '#', 'X', '#', '.'},
        {'#', '.', '.', '#', '#'},
        {'.', '#', '#', '.', '#'}};
    static char[][] v2 = new char[][]{{'#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#'},
        {'#', '.', '#', '#', '#'},
        {'#', '.', '.', 'X', '#'},
        {'#', '#', '#', '#', '#'}};
    static char[][] v3 = new char[][]{{'#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '.', '#', '.', '.', '.', '#'},
        {'#', '#', '#', '.', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '.', '.', 'X', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    public static void main(String[] args) {

        System.out.println(pakene(v1));
        System.out.println(pakene(v2));
        System.out.println(pakene(v3));

    }
}