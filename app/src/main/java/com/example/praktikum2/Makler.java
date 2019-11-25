package com.example.praktikum2;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class Makler implements Parcelable {

    private ArrayList<Immobilien> meineImmobilien;

    public Makler() {
        meineImmobilien = new ArrayList<Immobilien>();
    }

    public void addImmobilie(Immobilien immobilie) {
        meineImmobilien.add(immobilie);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Makler createFromParcel(Parcel in) {
            return new Makler();
        }
        public Makler[] newArray(int size) {
            return new Makler[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {

    }

    public int describeContents() {
        return 0;
    }
}