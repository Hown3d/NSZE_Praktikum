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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

//Implementiert Listener um fragments bzw Activisions zu öffnen wenn auf ein item im drawer Menü geklickt wird
public class Kunde_Uebersicht extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Navigation drawer
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Kunde kunde;
    private Bundle kundenBundle;

    Favoriten_Fragment favoriten;
    Kunde_Suchen_Fragment suche;
    Suchergebnisse_Fragment results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunde__uebersicht);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer finden und ihm ActionBarDrawerToggle hinzufügen
        drawer = findViewById(R.id.Kunde_Uebersicht);
        //Actiondrawertoggle sorgt dafür dass man das menü nicht nur per swipen öffnen kann sondern auch per knopf (vergleichbar zu ActionListener)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //sorgt dafür dass sich das icon dreht während dem swipen
        toggle.syncState();

        //Navigantionview (Definiert in activity_Kunden_Übersicht.xml) finden und diese Klasse als Listener hinzufuegen
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        results = new Suchergebnisse_Fragment();

        kunde = new Kunde("Peter");

        kundenBundle = new Bundle();
        kundenBundle.putParcelable("Kunde",kunde);

        favoriten = new Favoriten_Fragment();
        favoriten.setArguments(kundenBundle);

        suche = new Kunde_Suchen_Fragment();
        suche.setArguments(kundenBundle);

        toolbar.setTitle("Favoriten");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,favoriten).commit();
        }

    }

    //Methode aus Interface dient zum erkennen wenn ein Menüeintrag geklickt wird und öffnet diesen
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        //Switch-case mit Menuitem id je nach dem welcher Menüeintrag angeklickt wurde das entsprechende Fragment öffnen
        switch (menuItem.getItemId()){
            case R.id.nav_Favoriten:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, favoriten).commit();
                toolbar.setTitle("Favoriten");
                results.setInvisible();
                break;
            case R.id.nav_Immobilie_Suchen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, suche).commit();
                toolbar.setTitle("Immobilie Suchen");
                results.setInvisible();
                break;
            case R.id.nav_home:
                intent = new Intent(Kunde_Uebersicht.this, MainActivity.class);
                results.setInvisible();
                startActivity(intent);
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

    //Methode um Suchergebnisse Anzuzeigen wird von Kune_suchen_Fragment aufgerufen
    public void showResults(ArrayList<Immobilien> immobilien){
        results.setparams(immobilien);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, results).commit();
        results.setVisible();
        toolbar.setTitle("Suchergebnisse");
    }


    //Falls zurück gerückt wird während das Menü offen ist soll das Menü geschlossen werden und nicht die Activity verlassen werden
    public void onBackPressed() {
        //wenn drawer sichtbar ist diesen schliessen ansonsten Activity verlassen
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(results.visible()){
            //wenn Die Suchergebnisse Sichtbar sind soll zur Suche urückgekehrt werden
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, suche).commit();
            toolbar.setTitle("Immobilie Suchen");
            results.setInvisible();
        } else {
            super.onBackPressed();
        }
    }


}
