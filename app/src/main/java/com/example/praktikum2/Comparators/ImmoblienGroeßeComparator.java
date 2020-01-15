package com.example.praktikum2.Comparators;

import com.example.praktikum2.Immobilien;

import java.util.Comparator;

public class ImmoblienGroeßeComparator implements Comparator<Immobilien> {

    @Override
    public int compare(Immobilien o1, Immobilien o2) {
        if (o1.getGroeße() > o2.getGroeße()) {
            return 1;
        } else if (o1.getGroeße() < o2.getGroeße()) {
            return -1;
        } else {
            return 0;
        }
    }
}
