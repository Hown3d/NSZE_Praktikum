package com.example.praktikum2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Kunde implements Parcelable {
    static int maxkNr = 1;

    private ArrayList<Immobilien> favoriten;
    String name;
    int kundenNr;


    public Kunde(Parcel in){
        this.name = in.readString();
        this.kundenNr = in.readInt();
        this.favoriten = in.readArrayList(Immobilien.class.getClassLoader());
    }

    public Kunde(String name) {
        this.name = name;
        kundenNr = maxkNr;
        maxkNr++;
        favoriten = new ArrayList<Immobilien>();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Kunde createFromParcel(Parcel in) {
            return new Kunde(in);
        }
        public Kunde[] newArray(int size) {
            return new Kunde[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(kundenNr);
        dest.writeList(favoriten);
    }
    public int describeContents() {
        return 0;
    }



    public ArrayList<Immobilien> getFavoriten() {
        return favoriten;
    }

    public String getName() {
        return name;
    }

    public int getKundenNr() {
        return kundenNr;
    }

    public void addFavorit(Immobilien favorit){
        favoriten.add(favorit);
    }

    public void createSampleFavoriten(){
        favoriten.add(new Immobilien(25, 2, 500.00,4, "Kleine StudentenWG", "64347", 'M', null));
        favoriten.add(new Immobilien(75, 4, 1750.50,2, "2 Zimmer Küche Bad", "64283", 'M', null));
        favoriten.add(new Immobilien(5000, 50, 5000000.00,1.5, "Schloss Neuschwanstein", "87645", 'K', null));
        favoriten.add(new Immobilien(300, 7, 2000.00,2, "Landhaus", "65618", 'M', null));
        favoriten.add(new Immobilien(500, 12, 100000.00,4, "Penthouse", "10001", 'K', null));
    }
}