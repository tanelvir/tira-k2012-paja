
public class PuuMuunnos {
    

    public static Node muunna(Node tree) {
        // Toteuta minut
        return null;
    }

    static Node esim1 =
        new Node(1,null,
                 new Node(2,null,
                          new Node(3,null,
                                   new Node(4,null,
                                            new Node(5,null,
                                                     new Node(6))))));

    static Node esim2 =
        new Node(8,
                 new Node(3,
                          new Node(2,
                                   new Node(1),
                                   null),
                          new Node(6,
                                   new Node(5),
                                   new Node(7))),
                 new Node(9,
                          null,
                          new Node(10)));
                                       

    public static void main(String[] args) {

        System.out.println(esim1);
        System.out.println(muunna(esim1));
        System.out.println(esim2);
        System.out.println(muunna(esim2));

    }

}
