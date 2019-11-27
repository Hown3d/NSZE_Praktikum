package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.praktikum2.ui.Interfaces.FragmentSearchListener;
import com.google.android.material.navigation.NavigationView;

//Implementiert Listener um fragments bzw Activisions zu öffnen wenn auf ein item im drawer Menü geklickt wird
public class Kunde_Uebersicht extends AppCompatActivity implements FragmentSearchListener {

    //Navigation drawer
    private DrawerLayout drawer;

    private Kunde_Suchen_Fragment suchen_fragment;
    private Favoriten_Fragment favoriten_fragment;
    private Suchergebnisse_Fragment suchergebnisse_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde__uebersicht);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        suchen_fragment = new Kunde_Suchen_Fragment();
        favoriten_fragment = new Favoriten_Fragment();
        suchergebnisse_fragment = new Suchergebnisse_Fragment();

        //drawer finden und ihm ActionBarDrawerToggle hinzufügen
        drawer = findViewById(R.id.Kunde_Uebersicht);
        //Actiondrawertoggle sorgt dafür dass man das menü nicht nur per swipen öffnen kann sondern auch per knopf (vergleichbar zu ActionListener)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //sorgt dafür dass sich das icon dreht während dem swipen
        toggle.syncState();



        //Navigantionview (Definiert in activity_Kunden_Übersicht.xml) finden und diese Klasse als Listener hinzufuegen
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            //Methode aus Interface dient zum erkennen wenn ein Menüeintrag geklickt wird und öffnet diesen
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent;
                //Switch-case mit Menuitem id je nach dem welcher Menüeintrag angeklickt wurde das entsprechende Fragment öffnen
                switch (menuItem.getItemId()){
                    case R.id.nav_Favoriten:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, favoriten_fragment).commit();
                        break;
                    case R.id.nav_Immobilie_Suchen:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, suchen_fragment).commit();
                        break;
                    case R.id.nav_home:
                        intent = new Intent(Kunde_Uebersicht.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        //wenn die oncreate methode während der Laufzeit nochmal aufgerufen wird soll nicht immer wieder die Favoriten page geöffnet werden
        //savedInstance == null ist nur beim 1. öffnen der App der fall
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, favoriten_fragment).commit();
        }

    }

    //Falls zurück gerückt wird während das Menü offen ist soll das Menü geschlossen werden und nicht die Activity verlassen werden
    @Override
    public void onBackPressed() {
        //wenn drawer sichtbar ist diesen schliessen ansonsten Activity verlassen
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(suchergebnisse_fragment != null && suchergebnisse_fragment.isVisible()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,suchen_fragment).commit();
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public void Onsearchclicked(View view) {
        EditText edit_plz = view.findViewById(R.id.editText_standort_suchen);
        if(edit_plz.getText().toString().matches("")){
            suchergebnisse_fragment.setparams(0);
        }else{
            suchergebnisse_fragment.setparams(Integer.parseInt(edit_plz.getText().toString()));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,suchergebnisse_fragment).commit();
    }
}
