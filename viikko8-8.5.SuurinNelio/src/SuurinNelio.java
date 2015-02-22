public class SuurinNelio {

    public static int suurinNelio(boolean[][] kartta) {
        // Toteuta minut
        return 0;
    }

    static boolean[][] esim1 = new boolean[][] {{true, true, true, false,true, true},
                                                {true, false,true, true, true, true},
                                                {false,true, true, true, true, false},
                                                {true, false,true, true, true, false}};

    static boolean[][] esim2 = new boolean[][] {{true, true, true},
                                                {true, false,true},
                                                {true, true, true},
                                                {true, true, true}};
                                                

    public static void main(String[] args) {
        System.out.println(suurinNelio(esim1));
        System.out.println(suurinNelio(esim2));
    }

}