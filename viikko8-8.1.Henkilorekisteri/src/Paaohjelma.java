public class Paaohjelma {

    public static void main(String[] args) {
        Henkilorekisteri h = new Henkilorekisteri();
        h.lisaa("Sakke","Siikavirta",3);
        h.lisaa("Sakke","Siikavirta",4);
        h.lisaa("Sakke","Siikavirta",5);
        h.lisaa("Jokke","Siikavirta",4);
        h.lisaa("Jokke","Aaltonen",12);
        h.lisaa("Tinpe","Aaltonen",30);
        System.out.println(h.kysele("Sakke","Siikavirta",3));
        System.out.println(h.kysele("Sakke","Siikavirta",2));
        System.out.println(h.kysele("XXX","YYY",0));
        System.out.println(h.kysele("Tinpe","Aaltonen",30));

        for (String s : h.haeKaikki()) {
            System.out.println(s);
        }
    }

}