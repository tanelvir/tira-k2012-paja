
public class Sykli {

    public static int sykli(Node alku) {
        // Toteuta minut!
        return 0;
    }


    public static void main(String[] args) {
        Node vika = new Node(900);
        Node sykli = new Node(7, new Node(5, new Node(6, vika)));
        Node lista = new Node(1, new Node(2, new Node(3, sykli)));

        System.out.println("Syklitön: "+sykli(lista));

        vika.setNext(sykli);

        System.out.println("Syklillinen: "+sykli(lista));
    }

}
