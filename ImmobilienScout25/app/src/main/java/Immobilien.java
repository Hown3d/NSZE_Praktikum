import android.graphics.drawable.Drawable;

public class Immobilien {

    private Drawable bild;
    private String bezeichnung;
    private double preis;
    private int qradratmeter;
    private int anzZimmer;
    private boolean kaufen; //True = kaufen false = mieten
    private double provision;
    private String standort;

    public Immobilien(Drawable bild, String bezeichnung, double preis, int qradratmeter, int anzZimmer, boolean kaufen, double provision, String standort) {
        this.bild = bild;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.qradratmeter = qradratmeter;
        this.anzZimmer = anzZimmer;
        this.kaufen = kaufen;
        this.provision = provision;
        this.standort = standort;
    }








}
