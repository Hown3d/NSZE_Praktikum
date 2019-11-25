package com.example.praktikum2;

import android.graphics.drawable.Drawable;

public class Immobilien {

    private int groeße, anzZimmer;
    private double preis, maklerProv;
    private String bezeichnung, standort;
    private Drawable bild;

    public Immobilien(int groeße, int anzZimmer, double preis, double maklerProv, String bezeichnung, String standort, Drawable bild) {
        this.groeße = groeße;
        this.anzZimmer = anzZimmer;
        this.preis = preis;
        this.maklerProv = maklerProv;
        this.bezeichnung = bezeichnung;
        this.standort = standort;
        this.bild = bild;
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
