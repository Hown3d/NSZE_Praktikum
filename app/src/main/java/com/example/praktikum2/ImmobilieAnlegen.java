package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.muddzdev.styleabletoast.StyleableToast;

public class ImmobilieAnlegen extends Fragment {

    private Button button_commit;
    private EditText editText_bez, editText_groeße, editText_preis, editText_anzZimmer, editText_standort, editText_maklerProv;
    private Spinner mieten_kaufen_spinner;
    private ImageButton fotoButton;
    private Makler makler;
    private Intent intent;
    char mieten_kaufen;



    AdapterView.OnItemSelectedListener spinnerocl = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            mieten_kaufen = parent.getItemAtPosition(pos).toString().charAt(0);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    };

    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.fotoButton) {

            }
            if(id == R.id.button_commit) {
                try {
                    createImmobilie();
                } catch (Exception e) {
                    StyleableToast.makeText(getContext(),"Fehlendes Attribut!", R.style.MissingArgumentToast).show();
                }
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle maklerbundle = getArguments();
        makler = maklerbundle.getParcelable("makler");
        return inflater.inflate(R.layout.activity_immobilie_anlegen, container, false);
    }

    public void onActivityCreated(Bundle maklerbundle) {
        super.onActivityCreated(maklerbundle);
        init();
    }

    public void createImmobilie() throws Exception{
        int anzZimmer, groeße;
        String standort, bezeichnung;
        double maklerProv, preis;
        Bitmap bild;


        anzZimmer = Integer.parseInt(editText_anzZimmer.getText().toString());
        groeße = Integer.parseInt(editText_groeße.getText().toString());
        standort = editText_standort.getText().toString();
        bezeichnung = editText_bez.getText().toString();
        maklerProv = Double.parseDouble(editText_maklerProv.getText().toString());
        preis = Double.parseDouble(editText_preis.getText().toString());
        bild = ((BitmapDrawable)fotoButton.getDrawable()).getBitmap();

        Immobilien neueImmobilie = new Immobilien(groeße, anzZimmer, preis, maklerProv, bezeichnung, standort, mieten_kaufen, bild);
        makler.addImmobilie(neueImmobilie);

        intent = new Intent(ImmobilieAnlegen.this.getActivity(), Makler_Uebersicht.class);
        intent.putExtra("makler", makler);
        startActivity(intent);
    }

    public void init() {
        button_commit = getView().findViewById(R.id.button_commit);
        button_commit.setOnClickListener(ocl);

        mieten_kaufen_spinner = getView().findViewById(R.id.spinner_mieten_kaufen);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.Optionen_Immobilie, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mieten_kaufen_spinner.setAdapter(adapter);
        mieten_kaufen_spinner.setOnItemSelectedListener(spinnerocl);

        fotoButton = getView().findViewById(R.id.fotoButton);
        fotoButton.setOnClickListener(ocl);

        editText_bez = getView().findViewById(R.id.editText_bez);
        editText_groeße = getView().findViewById(R.id.editText_groeße);
        editText_preis = getView().findViewById(R.id.editText_preis);
        editText_anzZimmer = getView().findViewById(R.id.editText_anzZimmer);
        editText_standort = getView().findViewById(R.id.editText_standort);
        editText_maklerProv = getView().findViewById(R.id.editText_maklerprov);

    }
}
