package com.example.recycleviewclubs.adapter; // Assurez-vous que c'est le bon package

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recycleviewclubs.service.StarService; // Ajustez le chemin selon l'emplacement de votre classe StarService

import com.example.recycleviewclubs.beans.Star; // Modifiez le chemin en fonction de l'emplacement réel de la classe Star
import com.example.recycleviewclubs.R; // Assurez-vous que le package correspond à votre structure de projet

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.DialogInterface;
import android.app.AlertDialog;


public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable { // Implémentez Filterable

    private static final String TAG = "StarAdapter";
    private List<Star> stars; // Liste d'origine des étoiles
    private List<Star> starsFilter; // Liste filtrée des étoiles
    private Context context;
    private NewFilter mfilter; // Instance de NewFilter

    // Constructeur de l'adaptateur
    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars); // Initialiser la liste filtrée
        mfilter = new NewFilter(this); // Créer une instance de NewFilter
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate du layout pour chaque élément de la liste
        View view = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(view);

        // Ajoutez l'événement de clic pour afficher le popup
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate du layout du popup
                View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.idss);

                // Configurez le contenu du popup
                Bitmap bitmap = ((BitmapDrawable) ((ImageView) v.findViewById(R.id.img)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar) v.findViewById(R.id.stars)).getRating());
                idss.setText(((TextView) v.findViewById(R.id.ids)).getText().toString());

                // Créez et montrez le dialog
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Notez : ")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Star star = StarService.getInstance().findById(ids);
                                star.setStar(s);
                                StarService.getInstance().update(star);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();

                dialog.show();
            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Log.d(TAG, "onBindView call! " + position);

        // Chargement de l'image avec Glide
        Glide.with(context)
                .asBitmap()
                .load(starsFilter.get(position).getImg()) // Utilisation de la liste filtrée
                .apply(new RequestOptions().override(100, 100))
                .into(holder.img);

        // Configuration des données de l'étoile
        holder.name.setText(starsFilter.get(position).getName().toUpperCase()); // Utilisation de la liste filtrée
        holder.stars.setRating(starsFilter.get(position).getStar());
        holder.idss.setText(String.valueOf(starsFilter.get(position).getId())); // Utilisation de String.valueOf pour éviter les erreurs de type
    }

    @Override
    public int getItemCount() {
        return starsFilter.size(); // Retourner la taille de la liste filtrée
    }

    // Méthode pour obtenir le filtre
    @Override
    public Filter getFilter() {
        return mfilter;
    }

    // ViewHolder pour chaque élément de la liste
    public static class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    // Classe NewFilter pour gérer le filtrage
    public class NewFilter extends Filter {
        public StarAdapter mAdapter;

        public NewFilter(StarAdapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filteredList = new ArrayList<>(); // Liste pour stocker les résultats filtrés

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(stars); // Si aucun filtre, ajouter toutes les étoiles
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim(); // Modifiez le motif de filtre

                for (Star star : stars) {
                    // Vérifiez si le nom commence par le motif de filtre
                    if (star.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(star); // Ajouter à la liste filtrée
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList; // Assignation des résultats filtrés
            results.count = filteredList.size(); // Compte des résultats
            return results; // Retour des résultats
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter.clear(); // Vider la liste filtrée
            starsFilter.addAll((List) filterResults.values); // Ajouter les résultats filtrés
            mAdapter.notifyDataSetChanged(); // Notifier l'adaptateur pour rafraîchir la vue
        }
    }
}
