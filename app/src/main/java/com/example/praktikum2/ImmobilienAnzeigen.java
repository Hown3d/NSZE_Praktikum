package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
public class ImmobilienAnzeigen extends Fragment {

    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton fotobutton;
    private LinearLayout immobilie_Layout;
    private Makler makler;
    private Bundle maklerbundle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_immobilien_anzeigen, container, false);
        maklerbundle = getArguments();
        makler = maklerbundle.getParcelable("makler");


        if (makler.getMeineImmobilien().size() == 0) {
            //aus json Datei lesen
            JSONHandler jsonHandler = new JSONHandler(getActivity());
            try {
                makler.setMeineImmobilien(jsonHandler.getImmobilienFromJSON());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Immobilien konnten nicht importiert werden");
            }
        }

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
