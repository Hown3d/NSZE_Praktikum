package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ImmobilieAnlegen extends AppCompatActivity {

    private Button button_commit;
    private EditText editText_bez, editText_groeße, editText_preis, editText_anzZimmer, editText_standort, editText_maklerProv;
    private ImageButton fotoButton;
    private Makler makler;

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int anzZimmer, groeße;
            String standort, bezeichnung;
            double maklerProv, preis;

            anzZimmer = Integer.parseInt(editText_anzZimmer.getText().toString());
            groeße = Integer.parseInt(editText_groeße.getText().toString());
            standort = editText_anzZimmer.getText().toString();
            bezeichnung = editText_anzZimmer.getText().toString();
            maklerProv = Double.parseDouble(editText_maklerProv.getText().toString());
            preis = Double.parseDouble(editText_preis.getText().toString());

            Immobilien neueImmobilie = new Immobilien(groeße, anzZimmer, preis, maklerProv, bezeichnung, standort);
            makler.addImmobilie(neueImmobilie);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immobilie_anlegen);
        Intent intent = getIntent();
        if(intent.hasExtra("makler")) {
            makler = intent.getParcelableExtra("makler");
        }
        init();
    }

    public void init() {
        button_commit = findViewById(R.id.button_commit);
        button_commit.setOnClickListener(ocl);

        editText_bez = findViewById(R.id.editText_bez);
        editText_groeße = findViewById(R.id.editText_groeße);
        editText_preis = findViewById(R.id.editText_preis);
        editText_anzZimmer = findViewById(R.id.editText_anzZimmer);
        editText_standort = findViewById(R.id.editText_standort);
        editText_maklerProv = findViewById(R.id.editText_maklerprov);

    }
}
