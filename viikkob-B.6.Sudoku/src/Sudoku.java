import java.util.Scanner;
public class Sudoku {


    public static void ratkaise(int[][] sudoku) {
        // Toteuta minut
    }

    public static void main(String[] args) {
        int[][] sudoku = new int[9][9];
        Scanner lukija = new Scanner(System.in);
        System.out.println("Anna sudoku:");
        for (int i = 0; i < 9; i++) {
            String rivi = lukija.nextLine();
            for (int j = 0; j < 9; j++) {
                if (rivi.charAt(j) != '?') {
                    sudoku[i][j] = rivi.charAt(j) - '0';
                }
            }
        }
        ratkaise(sudoku);
        System.out.println("Ratkaisu:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }

    }
}
