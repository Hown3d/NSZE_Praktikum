package com.example.praktikum2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum2.R;

import java.util.ArrayList;
import java.util.Comparator;

public class Kunde_Suchen_Fragment extends Fragment {

    View view;

    ArrayList<Immobilien> immobilienFull;
    ArrayList<Immobilien> immobilien;

    EditText preis_ET, anzzimmer_ET,groesse_ET,standort_ET;

    double preis = -1;
    int anzZimmer = -1,groesse = -1;
    String standort = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.immobilie_suchen_fragment,container,false);


        preis_ET = view.findViewById(R.id.editText_preis_suchen);
        anzzimmer_ET = view.findViewById(R.id.editText_anzZimmer_suchen);
        groesse_ET = view.findViewById(R.id.editText_groeße_suchen);
        standort_ET = view.findViewById(R.id.editText_standort_suchen);

        preis_ET.addTextChangedListener(editTextWatcher);
        anzzimmer_ET.addTextChangedListener(editTextWatcher);
        groesse_ET.addTextChangedListener(editTextWatcher);
        standort_ET.addTextChangedListener(editTextWatcher);

        JSONHandler jsonHandler = new JSONHandler(getActivity());

        try {
            immobilienFull = jsonHandler.getImmobilienFromJSON();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Immobilien konnten nicht importiert werden");
        }

        immobilienFull.sort(new Comparator<Immobilien>() {
            @Override
            public int compare(Immobilien o1, Immobilien o2) {
                if(o1.getPreis() > o2.getPreis()){
                    return 1;
                }else if (o1.getPreis() < o2.getPreis()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });

        immobilien = new ArrayList<>(immobilienFull);

        ImmobilienAdapter immoAdapter = new ImmobilienAdapter(getContext(),immobilien);

        Button search_button = view.findViewById(R.id.button_suchen);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResults();
            }
        });


        return view;
    }


    public void openResults(){
        searchImmobilien();
        Kunde_Uebersicht act = (Kunde_Uebersicht)getActivity();
        act.showResults(immobilien);
        immobilien = new ArrayList<>(immobilienFull);
    }

    TextWatcher editTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                preis = Double.parseDouble(preis_ET.getText().toString());
                anzZimmer = Integer.parseInt(anzzimmer_ET.getText().toString());
                groesse = Integer.parseInt(groesse_ET.getText().toString());
            }catch (Exception e){

            }
            standort = standort_ET.getText().toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void searchImmobilien(){
        char mieten_kaufen;

        Spinner mieten_kaufen_spinner = view.findViewById(R.id.spinner_mieten_kaufen_suchen);
        mieten_kaufen = mieten_kaufen_spinner.getSelectedItem().toString().charAt(0);

        for(Immobilien immo: immobilienFull){
            if(immo.getMieten_kaufen() != mieten_kaufen){
                immobilien.remove(immo);
            }
        }


        if(!standort.equals("")){
            for(Immobilien immo: immobilien){
                if(immo.getStandort() != standort){
                    immobilien.remove(immo);
                }
            }
        }

        if(preis > 0){
            for(Immobilien immo: immobilien){
                if(immo.getPreis() > preis){
                    immobilien.remove(immo);
                }
            }
        }

        if(groesse > 0){
            for(Immobilien immo: immobilien){
                if(immo.getGroeße() < groesse){
                    immobilien.remove(immo);
                }
            }
        }

        if(anzZimmer > 0){
            for(Immobilien immo: immobilien){
                if(immo.getAnzZimmer() < anzZimmer){
                    immobilien.remove(immo);
                }
            }
        }

    }
}
