import java.util.Vector;

public class kunde {
    static int maxkNr = 1;

    private Vector<Immobilien> favoriten;
    String name;
    int kundenNr;


    public kunde(String name) {
        this.name = name;
        kundenNr = maxkNr;
        maxkNr++;
    }



}
