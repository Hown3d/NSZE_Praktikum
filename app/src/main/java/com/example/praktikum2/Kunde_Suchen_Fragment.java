package com.example.praktikum2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.praktikum2.ui.Interfaces.FragmentSearchListener;

public class Kunde_Suchen_Fragment extends Fragment {
    Button suchen;
    FragmentSearchListener listener;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.immobilie_suchen_fragment, container, false);

        suchen = view.findViewById(R.id.button_suchen);

        suchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.Onsearchclicked(view);
            }
        });
        return view;
    }

    //on Attach set the Listener to be the Activity which attached the fragment if not throw error
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentSearchListener){
            listener = (FragmentSearchListener)context;
        }else{
            throw new RuntimeException(context.toString() +"Must Implement Interface!");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
