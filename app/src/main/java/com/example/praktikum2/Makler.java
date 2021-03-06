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
    public void setMeineImmobilien(ArrayList<Immobilien> meineImmobilien) {
        this.meineImmobilien = meineImmobilien;
    }
}
