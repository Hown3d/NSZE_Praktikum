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
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
            try {
                String jsonline;
                File jsonfile = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + "/json_data.json");
                BufferedReader jsonReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonfile)));
                while ((jsonline = jsonReader.readLine()) != null) {
                    JSONObject jsonObject = new JSONObject(jsonline);
                    String bildfpad = null;
                    try {
                        bildfpad = jsonObject.getString("bildpfad");
                    } catch (JSONException nobild) {
                    }

                    makler.addImmobilie(new Immobilien(jsonObject.getInt("groeße"),
                            jsonObject.getInt("anzZimmer"),
                            jsonObject.getDouble("preis"),
                            jsonObject.getDouble("maklerProv"),
                            jsonObject.getString("bezeichnung"),
                            jsonObject.getString("standort"),
                            jsonObject.getString("mieten_kaufen").charAt(0),
                            bildfpad));

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("JsonFile konnte nicht gelesen werden");
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
