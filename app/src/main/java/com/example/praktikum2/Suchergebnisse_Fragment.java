package com.example.praktikum2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Suchergebnisse_Fragment extends Fragment {
    private int plz = 0;
    View v;
    TextView header;

    public void setparams(int plz){
        this.plz = plz;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.suchergebnisse_fragment,container,false);

        header = v.findViewById(R.id.suchergebnisse_Header);
        header.setText("Ergebnisse in " +plz);
        return v;
    }
}
