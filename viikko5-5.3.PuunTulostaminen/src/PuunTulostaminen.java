import java.util.ArrayDeque;

public class PuunTulostaminen {

    public static void print(Tree tree) {
    }

    // Sama puu kuin tehtävänannon esimerkissä
    static Tree esim =
        new Tree(new Node(1,null,
                          new Node(2,null,
                                   new Node(4,null),
                                   new Node(5,null)),
                          new Node(3,null,
                                   null,
                                   new Node(6,null,
                                            new Node(7,null),
                                            new Node(8,null)))));
                         

    public static void main(String[] args) {

        print(esim);

    }

}