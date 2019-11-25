package com.example.praktikum2;

public class Immobilien {

    private int groeße, anzZimmer;
    private double preis, maklerProv;
    private String bezeichnung, standort;

    public Immobilien(int groeße, int anzZimmer, double preis, double maklerProv, String bezeichnung, String standort) {
        this.groeße = groeße;
        this.anzZimmer = anzZimmer;
        this.preis = preis;
        this.maklerProv = maklerProv;
        this.bezeichnung = bezeichnung;
        this.standort = standort;
    }

    public int getGroeße() {
        return groeße;
    }

    public int getAnzZimmer() {
        return anzZimmer;
    }

    public double getPreis() {
        return preis;
    }

    public double getMaklerProv() {
        return maklerProv;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getStandort() {
        return standort;
    }

}
