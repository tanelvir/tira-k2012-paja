
import java.util.PriorityQueue;



public class Vuorikiipeilija {
    
    static PriorityQueue<Paikka> vuoret;
    
    

    public static int kiipea(int[][] vuori) {
        vuoret = new PriorityQueue<Paikka>();
        
    }


    public static int[][] vuori1 = new int[][] {
        {1,2,3,4,5},
        {2,1,3,3,6},
        {3,3,9,3,9},
        {4,3,2,4,7},
        {5,7,5,9,6}
    };

    public static void main(String[] args) {
        System.out.println(kiipea(vuori1));
    }

}
