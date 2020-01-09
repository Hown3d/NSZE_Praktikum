package com.example.praktikum2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImmobilienAdapter extends RecyclerView.Adapter<ImmobilienAdapter.ImmobilienViewHolder> {

    private ArrayList<Immobilien> mImmobilienListe;
    private Context mContext;

    public static class ImmobilienViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView TextMeineImmo_bez, TextMeineImmo_standort, TextMeineImmo_mieten_kaufen, TextMeineImmo_preis, TextMeineImmo_maklerprov, TextMeineImmo_groeße, TextMeineImmo_anzZimmer;

        public ImmobilienViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            TextMeineImmo_bez = itemView.findViewById(R.id.meineImmo_bez);
            TextMeineImmo_standort = itemView.findViewById(R.id.meineImmo_standort);
            TextMeineImmo_anzZimmer = itemView.findViewById(R.id.meineImmo_anzZimmer);
            TextMeineImmo_mieten_kaufen = itemView.findViewById(R.id.meineImmo_mieten_kaufen);
            TextMeineImmo_preis = itemView.findViewById(R.id.meineImmo_preis);
            TextMeineImmo_maklerprov = itemView.findViewById(R.id.meineImmo_maklerprov);
            TextMeineImmo_groeße = itemView.findViewById(R.id.meineImmo_groeße);
        }
    }

    public ImmobilienAdapter(Context context, ArrayList<Immobilien> immobilienListe) {
        mImmobilienListe = immobilienListe;
        mContext = context;
    }

    @NonNull
    @Override
    public ImmobilienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.immobilien_item_layout2, parent, false);
        ImmobilienViewHolder ivh = new ImmobilienViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImmobilienViewHolder holder, int position) {
        boolean mieten = true;

        Immobilien currentImmo = mImmobilienListe.get(position);

        if(currentImmo.getBildPfad() != null){
            holder.mImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeFile(currentImmo.getBildPfad()),2048,2048,false));

        }else{
            holder.mImageView.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.default_foto));
        }

        holder.TextMeineImmo_anzZimmer.setText("Anzahl Zimmer: " + currentImmo.getAnzZimmer());

        if(currentImmo.getMieten_kaufen() == 'M') {
            holder.TextMeineImmo_mieten_kaufen.setText("Zu Mieten");
        } else {
            holder.TextMeineImmo_mieten_kaufen.setText("Zu Kaufen");
            mieten = false;
        }

        holder.TextMeineImmo_standort.setText(currentImmo.getStandort());


        holder.TextMeineImmo_maklerprov.setText(currentImmo.getMaklerProv() + "%");

        holder.TextMeineImmo_bez.setText(currentImmo.getBezeichnung());
        if(mieten) {
            holder.TextMeineImmo_preis.setText((currentImmo.getPreis() + "€ pro Monat"));
        } else {
            holder.TextMeineImmo_preis.setText(currentImmo.getPreis() + "€");
        }

        holder.TextMeineImmo_groeße.setText((currentImmo.getGroeße() + "m²"));
    }

    @Override
    public int getItemCount() {
        return mImmobilienListe.size();
    }


}
