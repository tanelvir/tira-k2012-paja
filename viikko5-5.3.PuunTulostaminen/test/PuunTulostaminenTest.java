import java.util.Random;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.MockInOut;
import java.util.Scanner;

@Points("5.3")
public class PuunTulostaminenTest {

    static String p(Node n) {
        MockInOut mio = new MockInOut("");
        try {
            PuunTulostaminen.print(t(n));
            return normalize(mio.getOutput());
        } finally {
            mio.close();
        }
    }

    static String normalize(String s) {
        return s.trim().replaceAll("\n"," RET ").replaceAll(" +"," ");
    }

    static Tree t(Node n) {
        return new Tree(n);
    }

    static Node n(int key) {
        return new Node(key, null);
    }

    static Node n(int key, Node left, Node right) {
        return new Node(key, null, left, right);
    }

    @Test
    public void testaaPienia() {

        assertEquals("Tulostit yhden solmun puun väärin. (RET tarkoittaa rivinvaihtoa)",
                     "5",
                     p(n(5)));

        assertEquals("Tulostit kahden solmun puun väärin. (RET tarkoittaa rivinvaihtoa)",
                     "_5 RET 6",
                     p(n(5,
                         n(6),
                         null)));

        assertEquals("Tulostit kahden solmun puun väärin. (RET tarkoittaa rivinvaihtoa)",
                     "5_ RET 6",
                     p(n(5,
                         null,
                         n(6))));
                         
        assertEquals("Tulostit kolmen solmun puun väärin. (RET tarkoittaa rivinvaihtoa)",
                     "_1_ RET 2 3",
                     p(n(1,
                         n(2),
                         n(3))));

    }

    @Test
    public void testaaIsompia() {

        assertEquals("Tulostit esimerkin väärin. (RET tarkoittaa rivinvaihtoa)",
                     "_1_ RET _2_ 3_ RET 4 5 _6_ RET 7 8",
                     p(new Node(1,null,
                                new Node(2,null,
                                         new Node(4,null),
                                         new Node(5,null)),
                                new Node(3,null,
                                         null,
                                         new Node(6,null,
                                                  new Node(7,null),
                                                  new Node(8,null))))));

        assertEquals("Tulostit ison puun väärin. (RET tarkoittaa rivinvaihtoa)",
                     "_1_ RET _2 12_ RET _3_ _16_ RET 4 5 17 18_ RET _19 RET 20_ RET 21",
                     p(n(1,
                         n(2,
                           n(3,
                             n(4),
                             n(5)),
                           null),
                         n(12,
                           null,
                           n(16,
                             n(17),
                             n(18,
                               null,
                               n(19,
                                 n(20,
                                   null,
                                   n(21)),
                                 null)))))));                     
                         

    }


}