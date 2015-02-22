import java.util.Random;

public class Hakupuu {

    public static Object search(Tree tree, int key) {
        // TOTEUTA MINUT
        return null;
    }

    public static void insert(Tree tree, int key, Object value) {
        // TOTEUTA MINUT
    }

    // 
    static Tree puu =
        new Tree(new Node(7,"X",
                          new Node(3,"Z",
                                   new Node(1,"Y"),
                                   new Node(5,"G",
                                            null,
                                            new Node(6,"A"))),
                          new Node(8,"J",
                                   null,
                                   new Node(17,"H",
                                            new Node(10,"E"),
                                            new Node(19,"F")))));
    
    
                                                   

    public static void main(String[] args) {

        System.out.println("Hakuja:");

        for (int i=1; i<20; i++) {
            System.out.println(i+":"+search(puu, i));
        }

        Random r = new Random();

        Tree tree = new Tree();

        int MAX = 40;

        System.out.println("Lisäyksiä:");

        for (int i=0; i<20; i++) {
            int key = r.nextInt(40);
            System.out.println("Lisätään: "+key+" "+i);
            insert(tree, key, i);
        }

        System.out.println("Puu lisäysten jälkeen:\n"+tree+"\nHakuja:");

        for (int i=0; i<20; i++) {
            int key = r.nextInt(40);
            System.out.println(key+":"+search(tree, key));
        }
    }

}
