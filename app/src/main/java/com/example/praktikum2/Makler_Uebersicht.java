package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class Makler_Uebersicht extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Makler makler;
    private Intent intent;
    private DrawerLayout drawer;
    private Bundle maklerbundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makler__uebersicht);
        intent = getIntent();
        if(intent.hasExtra("makler")) {
            makler = intent.getParcelableExtra("makler");
        }

        Toolbar toolbar = findViewById(R.id.toolbar_makler);
        try {
          setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //drawer finden und ihm ActionBarDrawerToggle hinzufügen
        drawer = findViewById(R.id.Makler_Uebersicht);
        //Actiondrawertoggle sorgt dafür dass man das menü nicht nur per swipen öffnen kann sondern auch per knopf (vergleichbar zu ActionListener)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //sorgt dafür dass sich das icon dreht während dem swipen
        toggle.syncState();

        //Navigantionview (Definiert in activity_Kunden_Übersicht.xml) finden und diese Klasse als Listener hinzufuegen
        NavigationView navigationView = findViewById(R.id.nav_view_makler);
        navigationView.setNavigationItemSelectedListener(this);


        if(savedInstanceState == null){
            maklerbundle = new Bundle();
            maklerbundle.putParcelable("makler", makler);
            ImmobilienAnzeigen immobilienAnzeigen_fragment = new ImmobilienAnzeigen();
            immobilienAnzeigen_fragment.setArguments(maklerbundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_makler, immobilienAnzeigen_fragment).commit();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Switch-case mit Menuitem id je nach dem welcher Menüeintrag angeklickt wurde das entsprechende Fragment öffnen
        maklerbundle = new Bundle();
        //Makler Objekt in das Bundle legen
        maklerbundle.putParcelable("makler", makler);
        switch (menuItem.getItemId()){
            case R.id.nav_Meine_Immobilien:
                //Fragment erstellen
                Fragment immobilienAnzeigen_fragment = new ImmobilienAnzeigen();
                //Bundle mit Makler Objekt an das Fragment attachen
                immobilienAnzeigen_fragment.setArguments(maklerbundle);
                //neues Fragment mit altem ersetzen
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_makler, immobilienAnzeigen_fragment).commit();
                break;
            case R.id.nav_Immobilie_Erstellen:
                //Fragment erstellen
                Fragment immobilienAnlegen_fragment = new ImmobilieAnlegen();
                //Bundle mit Makler Objekt an das Fragment attachen
                immobilienAnlegen_fragment.setArguments(maklerbundle);
                //neues Fragment mit altem ersetzen
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_makler, immobilienAnlegen_fragment).commit();
                break;
            case R.id.nav_home_makler:
                intent = new Intent(Makler_Uebersicht.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

    protected void onResume() {
        super.onResume();
    }

    public void init() {
//        button_meineImmobilien = findViewById(R.id.button_meineImmobilien);
//        button_neueImmobilie = findViewById(R.id.button_neueImmobilie);
//        button_meineImmobilien.setOnClickListener(ocl);
//        button_neueImmobilie.setOnClickListener(ocl);
    }

    //Falls zurück gerückt wird während das Menü offen ist soll das Menü geschlossen werden und nicht die Activity verlassen werden
    public void onBackPressed() {
        //wenn drawer sichtbar ist diesen schliessen ansonsten Activity verlassen
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
