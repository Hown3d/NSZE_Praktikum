package com.example.praktikum2;

import android.app.Activity;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class JSONHandler {

    private Activity activity;
    private ArrayList<Immobilien> immobilienFromJSON;

    public JSONHandler(Activity activity) {
        this.activity = activity;
        immobilienFromJSON = new ArrayList<>();
    }

    public ArrayList<Immobilien> getImmobilienFromJSON() throws Exception {
        String jsonline;
        File jsonfile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath() + "/json_data.json");
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonfile)));
        while ((jsonline = jsonReader.readLine()) != null) {
            JSONObject jsonObject = new JSONObject(jsonline);
            String bildfpad = null;
            try {
                bildfpad = jsonObject.getString("bildpfad");
            } catch (JSONException nobild) {
            }

            immobilienFromJSON.add(new Immobilien(jsonObject.getInt("groeße"),
                    jsonObject.getInt("anzZimmer"),
                    jsonObject.getDouble("preis"),
                    jsonObject.getDouble("maklerProv"),
                    jsonObject.getString("bezeichnung"),
                    jsonObject.getString("standort"),
                    jsonObject.getString("mieten_kaufen").charAt(0),
                    bildfpad));
        }
        jsonReader.close();
        return immobilienFromJSON;
    }

    public void persistImmobilieToJSON(int anzZimmer, int groeße, String standort, String bezeichnung, double maklerProv, double preis, char Mmieten_kaufen, String bildpfad) throws Exception {
        //Object persistieren
        JSONObject jsonImmobilie = new JSONObject();

        File storagedir = activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File jsonfile = new File(storagedir.getPath() + "/json_data.json");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(jsonfile, true));


        jsonImmobilie.put("groeße", groeße);
        jsonImmobilie.put("anzZimmer", anzZimmer);
        jsonImmobilie.put("preis",preis);
        jsonImmobilie.put("maklerProv", maklerProv);
        jsonImmobilie.put("bezeichnung", bezeichnung);
        jsonImmobilie.put("standort", standort);
        jsonImmobilie.put("mieten_kaufen", Mmieten_kaufen);
        jsonImmobilie.put("bildpfad",bildpfad);

        //new Line wenn nicht der Erste Eintrag
        if(jsonfile.length() != 0) {
            bufferedWriter.newLine();
        }
        bufferedWriter.append(jsonImmobilie.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}