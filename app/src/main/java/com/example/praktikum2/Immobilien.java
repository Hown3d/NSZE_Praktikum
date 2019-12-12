package com.example.praktikum2;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Immobilien implements Parcelable {

    private int groeße, anzZimmer;
    private double preis, maklerProv;
    private String bezeichnung, standort;

    //ist hier String, da der Dateipfad und nicht das Bild hinterlegt wird
    private String bildpfad;

    private char mieten_kaufen;

    public Immobilien(int groeße, int anzZimmer, double preis, double maklerProv, String bezeichnung, String standort, char mieten_kaufen, String bildpfad) {
        this.groeße = groeße;
        this.anzZimmer = anzZimmer;
        this.preis = preis;
        this.maklerProv = maklerProv;
        this.bezeichnung = bezeichnung;
        this.standort = standort;
        this.bildpfad = bildpfad;
        this.mieten_kaufen = mieten_kaufen;
    }

    private Immobilien(Parcel in) {
        groeße = in.readInt();
        anzZimmer = in.readInt();
        preis = in.readDouble();
        maklerProv = in.readDouble();
        bezeichnung = in.readString();
        standort = in.readString();
        bildpfad= in.readString();
        mieten_kaufen = in.readString().charAt(0);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Immobilien createFromParcel(Parcel in) {
            return new Immobilien(in);
        }
        public Immobilien[] newArray(int size) {
            return new Immobilien[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(groeße);
        dest.writeInt(anzZimmer);
        dest.writeDouble(preis);
        dest.writeDouble(maklerProv);
        dest.writeString(bezeichnung);
        dest.writeString(standort);
        dest.writeString(bildpfad);
        dest.writeString(String.valueOf(mieten_kaufen));
    }
    public int describeContents() {
        return 0;
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

    public char getMieten_kaufen() {
        return mieten_kaufen;
    }

    public String getBildPfad() {
        return bildpfad;
    }


}
