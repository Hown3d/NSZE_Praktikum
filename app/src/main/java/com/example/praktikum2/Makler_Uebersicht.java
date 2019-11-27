package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Makler_Uebersicht extends AppCompatActivity {

    private Button button_neueImmobilie, button_meineImmobilien;
    private Makler makler;
    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makler__uebersicht);
        intent = getIntent();
        if(intent.hasExtra("makler")) {
            makler = intent.getParcelableExtra("makler");
        }
        init();
    }
    //ocl listener zum entscheiden, ob
    View.OnClickListener ocl = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if(id == R.id.button_neueImmobilie) {
                intent = new Intent(Makler_Uebersicht.this, ImmobilieAnlegen.class);
                intent.putExtra("makler", makler);
                startActivity(intent);

            }
            if(id == R.id.button_meineImmobilien) {
            intent = new Intent(Makler_Uebersicht.this, ImmobilienAnzeigen.class);
            intent.putExtra("makler", makler);
            startActivity(intent);
            }
        }
    };

    protected void onResume() {
        super.onResume();
    }

    public void init() {
        button_meineImmobilien = findViewById(R.id.button_meineImmobilien);
        button_neueImmobilie = findViewById(R.id.button_neueImmobilie);
        button_meineImmobilien.setOnClickListener(ocl);
        button_neueImmobilie.setOnClickListener(ocl);
    }

}
