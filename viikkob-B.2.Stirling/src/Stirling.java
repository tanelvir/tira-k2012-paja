public class Stirling {

    public static long stirling(long n, long k) {
        // Toteuta minut
        long luku = stirlings(n, k);
        if (luku < 0)
            return luku*-1;
        else
        return luku;
    }
    
    public static long stirlings(long n, long k){
        if (n==0 && k == 0)
            return 1;
        if (n==0 || k == 0)
            return 0;
        if (n == k)
            return 1;
        return stirlings(n-1, k-1) - k*stirlings(n-1, k);
    }
    
    public static void main(String[] args) {
        for (int n=0; n<6; n++) {
            for (int k=0; k<=n; k++) {
                System.out.println("S("+n+","+k+") = "+stirling(n,k));
            }
        }
    }

}