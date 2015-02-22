public class Paaohjelma {

    public static void main(String[] args) {
        Odotushuone o = new Odotushuone();
        o.lisaa("Pekka",3);
        o.lisaa("Liisa",4);
        System.out.println(o.seuraavaPotilas());
        o.lisaa("Simo",1);
        o.lisaa("Kerttu",99);
        System.out.println(o.seuraavaPotilas());
        System.out.println(o.seuraavaPotilas());
        System.out.println(o.seuraavaPotilas());
    }

}
