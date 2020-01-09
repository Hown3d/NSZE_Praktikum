package com.example.praktikum2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Suchergebnisse_Fragment extends Fragment {
    private int plz = 0;
    View v;
    TextView header;
    ArrayList<Immobilien> immobilien;

    boolean isVisible = false;

    public void setparams(ArrayList<Immobilien> immos){immobilien =immos;}

    public void setVisible(){isVisible = true;}
    public void setInvisible(){isVisible = false;}
    public boolean visible(){return isVisible;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.suchergebnisse_fragment,container,false);

        RecyclerView mRecylerView = v.findViewById(R.id.recyclerView_Suchergebnisse);
        mRecylerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        ImmobilienAdapter mAdapter = new ImmobilienAdapter(getContext(),immobilien);

        mRecylerView.setLayoutManager(mLayoutManager);
        mRecylerView.setAdapter(mAdapter);
        return v;
    }
}
