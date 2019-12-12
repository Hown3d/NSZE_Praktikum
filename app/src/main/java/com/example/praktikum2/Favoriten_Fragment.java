package com.example.praktikum2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum2.R;

public class Favoriten_Fragment extends Fragment {
    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Kunde kunde;
    private Bundle kundenbundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favoriten_fragment,container,false);
        kundenbundle = getArguments();
        kunde = kundenbundle.getParcelable("Kunde");

        //testObjekte erstellen
        if (kunde.getFavoriten().size() == 0)
            kunde.createSampleFavoriten();

        mRecylerView = view.findViewById(R.id.recyclerView_Favoriten);
        mRecylerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new ImmobilienAdapter(getContext(),kunde.getFavoriten());

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
