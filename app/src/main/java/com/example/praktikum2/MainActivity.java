package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Button button_makler, button_kunde;
    private Makler makler;
    private Kunde kunde;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    View.OnClickListener ocl = new View.OnClickListener() {
    public void onClick(View v) {
        Intent intent;
        int id = v.getId();
        if(id == R.id.button_makler) {
            intent = new Intent(MainActivity.this, Makler_Uebersicht.class);
            intent.putExtra("makler", new Makler());
            startActivity(intent);

        }
        if(id == R.id.button_kunde) {
            intent = new Intent(MainActivity.this, Kunde_Uebersicht.class);
            startActivity(intent);
        }
    }
};

    protected void onResume() {
        super.onResume();
    }
    public void init() {
        button_kunde = findViewById(R.id.button_kunde);
        button_makler = findViewById(R.id.button_makler);
        button_makler.setOnClickListener(ocl);
        button_kunde.setOnClickListener(ocl);
    }


}
