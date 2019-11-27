package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ImmobilienAnzeigen extends AppCompatActivity {

    private TextView TextMeineImmo_bez, TextMeineImmo_standort, TextMeineImmo_mieten_kaufen, TextMeineImmo_preis, TextMeineImmo_maklerprov, TextMeineImmo_groeße, TextMeineImmo_anzZimmer;
    private ImageButton fotobutton;
    private LinearLayout immobilie_Layout;
    private Makler makler;
    private Intent intent;

    View.OnClickListener fotoocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(ImmobilienAnzeigen.this, ImmobilienAnzeigen_Detail.class);
            intent.putExtra("makler", makler);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immobilien_anzeigen);
        intent = getIntent();
        if (intent.hasExtra("makler")) {
            makler = intent.getParcelableExtra("makler");
        }
        init();
    }

    public void fillViews() {
        char mieten_kaufen;
        Immobilien immobilie;
        ArrayList<Immobilien> meineImmobilien = makler.getMeineImmobilien();

        /* Ansatz für mehrere Immobilien, hier erstmal nur eine
        for(Immobilien immobilie : meineImmobilien) {

        }*/

        try {
            immobilie = meineImmobilien.get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return;
        }

        TextMeineImmo_bez.setText(immobilie.getBezeichnung());
        TextMeineImmo_anzZimmer.setText("Zimmer: " + immobilie.getAnzZimmer());
        TextMeineImmo_groeße.setText(immobilie.getGroeße() + "m²");
        TextMeineImmo_maklerprov.setText("MaklerProv: " + immobilie.getMaklerProv());

        mieten_kaufen = immobilie.getMieten_kaufen();
        switch (mieten_kaufen) {
            case 'K':
                TextMeineImmo_preis.setText(immobilie.getPreis() + "€");
                TextMeineImmo_mieten_kaufen.setText("zum kaufen");
            case 'M':
                TextMeineImmo_preis.setText(immobilie.getPreis() + "€ pro Monat");
                TextMeineImmo_mieten_kaufen.setText("zum mieten");
        }
        TextMeineImmo_standort.setText("In: " + immobilie.getStandort());

        fotobutton.setImageBitmap(immobilie.getBild());
        immobilie_Layout.setVisibility(View.VISIBLE);
    }

    public void init() {
        fotobutton = findViewById(R.id.imageButton_meineImmo);
        fotobutton.setOnClickListener(fotoocl);

        TextMeineImmo_bez = findViewById(R.id.meineImmo_bez);
        TextMeineImmo_standort = findViewById(R.id.meineImmo_standort);
        TextMeineImmo_mieten_kaufen = findViewById(R.id.meineImmo_mieten_kaufen);
        TextMeineImmo_preis = findViewById(R.id.meineImmo_preis);
        TextMeineImmo_maklerprov = findViewById(R.id.meineImmo_maklerprov);
        TextMeineImmo_groeße = findViewById(R.id.meineImmo_groeße);
        TextMeineImmo_anzZimmer = findViewById(R.id.meineImmo_anzZimmer);

        immobilie_Layout = findViewById(R.id.immobilie_Layout);
        fillViews();
    }
}
