package com.example.praktikum2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;



public class Makler implements Parcelable {

    private ArrayList<Immobilien> meineImmobilien;

    public Makler() {
        meineImmobilien = new ArrayList<>();
    }

    public Makler(Parcel in) {
        meineImmobilien = in.readArrayList(Immobilien.class.getClassLoader());
    }

    public void addImmobilie(Immobilien immobilie) {
        meineImmobilien.add(immobilie);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Makler createFromParcel(Parcel in) {
            return new Makler(in);
        }
        public Makler[] newArray(int size) {
            return new Makler[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(meineImmobilien);
    }
    public int describeContents() {
        return 0;
    }

    public ArrayList<Immobilien> getMeineImmobilien() {
        return meineImmobilien;
    }

    public void createTestImmobilien() {
        meineImmobilien.add(new Immobilien(25, 2, 500.00,4, "Kleine StudentenWG", "Darmstadt", 'M', null));
        meineImmobilien.add(new Immobilien(75, 4, 1750.50,2, "2 Zimmer Küche Bad", "Darmstadt", 'M', null));
        //meineImmobilien.add(new Immobilien(5000, 50, 5000000.00,1.5, "Schloss Neuschwanstein", "Neuschwanstein", 'K', null));
        //meineImmobilien.add(new Immobilien(300, 7, 2000.00,2, "Landhaus", "Selters", 'M', null));
        //meineImmobilien.add(new Immobilien(500, 12, 100000.00,4, "Penthouse", "New York", 'K', null));
    }
}
