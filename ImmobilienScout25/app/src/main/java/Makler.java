import java.security.PublicKey;
import java.util.Vector;

public class Makler {
    private static int maxmNr = 1;

    private String name;
    private Vector<Immobilien> inserate;
    private int maklerNr;

    public Makler(String name){
        this.name = name;
        maklerNr = maxmNr;
        maxmNr++;
    }

}
