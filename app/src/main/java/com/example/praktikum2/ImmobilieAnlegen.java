package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ImmobilieAnlegen extends AppCompatActivity {

    private Button button_commit;
    private EditText editText_bez, editText_groeße, editText_preis, editText_anzZimmer, editText_standort, editText_maklerProv;
    private Spinner mieten_kaufen_spinner;
    private ImageButton fotoButton;
    private Makler makler;
    private Intent intent;
    char mieten_kaufen;



    AdapterView.OnItemSelectedListener spinnerocl = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            mieten_kaufen = parent.getItemAtPosition(pos).toString().charAt(0);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    };

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.fotoButton) {

            }
            if(id == R.id.button_commit) {
                try {
                    createImmobilie();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immobilie_anlegen);
        intent = getIntent();
        if(intent.hasExtra("makler")) {
            makler = intent.getParcelableExtra("makler");
        }
        init();
    }

    public void createImmobilie() throws Exception{
        int anzZimmer, groeße;
        String standort, bezeichnung;
        double maklerProv, preis;
        Bitmap bild;


        anzZimmer = Integer.parseInt(editText_anzZimmer.getText().toString());
        groeße = Integer.parseInt(editText_groeße.getText().toString());
        standort = editText_standort.getText().toString();
        bezeichnung = editText_bez.getText().toString();
        maklerProv = Double.parseDouble(editText_maklerProv.getText().toString());
        preis = Double.parseDouble(editText_preis.getText().toString());
        bild = ((BitmapDrawable)fotoButton.getDrawable()).getBitmap();

        Immobilien neueImmobilie = new Immobilien(groeße, anzZimmer, preis, maklerProv, bezeichnung, standort, mieten_kaufen, bild);
        makler.addImmobilie(neueImmobilie);

        intent = new Intent(ImmobilieAnlegen.this, Makler_Uebersicht.class);
        intent.putExtra("makler", makler);
        startActivity(intent);
    }

    public void init() {
        button_commit = findViewById(R.id.button_commit);
        button_commit.setOnClickListener(ocl);

        mieten_kaufen_spinner = findViewById(R.id.spinner_mieten_kaufen);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Optionen_Immobilie, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mieten_kaufen_spinner.setAdapter(adapter);
        mieten_kaufen_spinner.setOnItemSelectedListener(spinnerocl);

        fotoButton = findViewById(R.id.fotoButton);
        fotoButton.setOnClickListener(ocl);

        editText_bez = findViewById(R.id.editText_bez);
        editText_groeße = findViewById(R.id.editText_groeße);
        editText_preis = findViewById(R.id.editText_preis);
        editText_anzZimmer = findViewById(R.id.editText_anzZimmer);
        editText_standort = findViewById(R.id.editText_standort);
        editText_maklerProv = findViewById(R.id.editText_maklerprov);

    }
}
