package com.example.praktikum2;

import java.util.ArrayList;

public class Kunde {
    static int maxkNr = 1;

    private ArrayList<Immobilien> favoriten;
    String name;
    int kundenNr;


    public Kunde(String name) {
        this.name = name;
        kundenNr = maxkNr;
        maxkNr++;
        favoriten = new ArrayList<Immobilien>();
    }



}