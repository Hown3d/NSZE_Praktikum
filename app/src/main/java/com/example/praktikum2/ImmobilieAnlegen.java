package com.example.praktikum2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.LruCache;
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
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImmobilieAnlegen extends Fragment {

    static final int REQUEST_TAKE_PHOTO = 1;

    private Button button_commit;
    private EditText editText_bez, editText_groeße, editText_preis, editText_anzZimmer, editText_standort, editText_maklerProv;
    private Spinner mieten_kaufen_spinner;
    private ImageButton fotoButton;
    private Makler makler;
    private Intent intent;
    char mieten_kaufen;

    //PhotoIntent
    String currentPhotoPath;

    private void previewPicture() {
        testCache();

        Bitmap b = BitmapFactory.decodeFile(currentPhotoPath);
        fotoButton.setImageBitmap(Bitmap.createScaledBitmap(b, 512, 512, false));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                System.out.println(photoFile.getAbsolutePath());
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("Exception dispatchTakePictureIntent");
            }
            // Continue only if the File was successfully created
            // directory must exist!
            if (photoFile != null) {
                // System.out.println("Photo: " + photoFile.getAbsolutePath());
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "photoprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == MainActivity.RESULT_OK) {
            Toast.makeText(getActivity().getApplicationContext(), "Foto aufgenommen", Toast.LENGTH_LONG).show();
            previewPicture();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // Standard directory in which to place pictures that are available to
        // the user. Note that this is primarily a convention for the top-level
        // public directory, as the media scanner will find and collect pictures
        // in any directory:         ... /files/Pictures
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // ... JPEG_2019...jpg
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );
        // Save file path
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void testCache() {
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        System.out.println("Pfad: " + storageDir.getAbsolutePath());
        File[] dateien = storageDir.listFiles();
        for (File f : dateien)
            System.out.println("Datei:" + f.getAbsolutePath());
    }


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
                dispatchTakePictureIntent();
            }
            if(id == R.id.button_commit) {
                try {
                    createImmobilie();
                } catch (NumberFormatException e) {
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

    private void createJsonImmobilie(int anzZimmer, int groeße, String standort, String bezeichnung, double maklerProv, double preis, char Mmieten_kaufen, String bildpfad) throws Exception {
        //Object persistieren
        JSONObject jsonImmobilie = new JSONObject();

        File storagedir = getActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File jsonfile = new File(storagedir.getPath() + "/json_data.json");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(jsonfile));


        jsonImmobilie.put("groeße", groeße);
        jsonImmobilie.put("anzZimmer", anzZimmer);
        jsonImmobilie.put("preis",preis);
        jsonImmobilie.put("maklerProv", maklerProv);
        jsonImmobilie.put("bezeichnung", bezeichnung);
        jsonImmobilie.put("standort", standort);
        jsonImmobilie.put("mieten_kaufen", Mmieten_kaufen);
        jsonImmobilie.put("bildpfad",bildpfad);

        outputStreamWriter.append(jsonImmobilie.toString());
        outputStreamWriter.close();
    }

    private void createImmobilie() throws NumberFormatException{
        int anzZimmer, groeße;
        String standort, bezeichnung;
        double maklerProv, preis;
        String bildpfad;

        anzZimmer = Integer.parseInt(editText_anzZimmer.getText().toString());
        groeße = Integer.parseInt(editText_groeße.getText().toString());
        standort = editText_standort.getText().toString();
        bezeichnung = editText_bez.getText().toString();
        maklerProv = Double.parseDouble(editText_maklerProv.getText().toString());
        preis = Double.parseDouble(editText_preis.getText().toString());

        if(TextUtils.isEmpty(bezeichnung) || TextUtils.isEmpty(bezeichnung)) {
            throw new NumberFormatException();
        }

        bildpfad = currentPhotoPath;
        Immobilien neueImmobilie = new Immobilien(groeße, anzZimmer, preis, maklerProv, bezeichnung, standort, mieten_kaufen, bildpfad);

        try {
            createJsonImmobilie(anzZimmer, groeße, standort, bezeichnung, maklerProv, preis,mieten_kaufen,bildpfad);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Can't safe this Immobilie as JSON");
        }

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
