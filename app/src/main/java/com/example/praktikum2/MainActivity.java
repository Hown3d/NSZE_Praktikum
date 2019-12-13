package com.example.praktikum2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private static int REQUEST_EXTERNALSTORAGE = 7;
    private Button button_makler, button_kunde;
    private Makler makler;
    private Kunde kunde;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNALSTORAGE);
        }
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
