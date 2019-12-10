package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ImmobilienAnzeigen extends Fragment {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton fotobutton;
    private LinearLayout immobilie_Layout;
    private Makler makler;
    private Bundle maklerbundle;

//    View.OnClickListener fotoocl = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            //Makler Objekt in das Bundle legen
//            maklerbundle.putParcelable("makler", makler);
//            //Fragment erstellen
//            Fragment immobilieAnzeigen_Detail = new ImmobilieAnlegen();
//            //Bundle mit Makler Objekt an das Fragment attachen
//            immobilieAnzeigen_Detail.setArguments(maklerbundle);
//            //neues Fragment mit altem ersetzen
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_makler, immobilieAnzeigen_Detail).commit();
//        }
//    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_immobilien_anzeigen, container, false);
        maklerbundle = getArguments();
        makler = maklerbundle.getParcelable("makler");

        //testObjekte erstellen
        if (makler.getMeineImmobilien().size() == 0)
            makler.createTestImmobilien();

        mRecylerView = view.findViewById(R.id.recyclerView);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ImmobilienAdapter(getContext(), makler.getMeineImmobilien());

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        return view;
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
