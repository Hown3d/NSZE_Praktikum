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
import android.widget.Toast;

import com.example.praktikum2.Comparators.ImmoblienGroeßeComparator;

import java.util.Collections;

public class ImmobilienAnzeigen extends Fragment {

    public RecyclerView mRecylerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    private Makler makler;
    private Bundle maklerbundle;
    private ImageButton toolbarbutton;


    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Collections.sort(makler.getMeineImmobilien(),new ImmoblienGroeßeComparator());
            //adapter benachrichtigen, dass sich die Daten geändert haben
            mAdapter.notifyDataSetChanged();
            Toast toast = Toast.makeText(getContext(), "Immobilien nach Größe sortiert!", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_immobilien_anzeigen, container, false);

        toolbarbutton = getActivity().findViewById(R.id.toolbar_sort);
        toolbarbutton.setVisibility(View.VISIBLE);
        toolbarbutton.setOnClickListener(ocl);

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

    public void onPause() {
        super.onPause();
        toolbarbutton.setVisibility(View.INVISIBLE);
    }
}
