package com.example.recycleviewclubs;

import android.os.Bundle;
import android.util.Log; // Assurez-vous d'importer Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.core.app.ShareCompat;

import android.view.Menu; // Ajoutez cette ligne
import android.view.MenuItem; // Assurez-vous également d'importer MenuItem si vous l'utilisez

import androidx.appcompat.widget.Toolbar;

import com.example.recycleviewclubs.adapter.StarAdapter; // Ajustez le chemin selon l'emplacement réel de la classe StarAdapter
import com.example.recycleviewclubs.beans.Star; // Modifiez le chemin en fonction de l'emplacement réel de la classe Star
import com.example.recycleviewclubs.R; // Assurez-vous que le package correspond à votre structure de projet
import com.example.recycleviewclubs.service.StarService; // Modifiez le chemin selon l'emplacement réel de StarService

import androidx.appcompat.widget.SearchView; // Importation pour SearchView

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service; // Déclaration du service
    private static final String TAG = "ListActivity"; // Déclaration de la variable TAG

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Initialiser la liste des étoiles
        stars = new ArrayList<>();
        service = StarService.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Configure la Toolbar comme ActionBar

        // Charger les données dans la liste
        init(); // Remplir la liste avec des étoiles

        // Initialiser le RecyclerView
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll()); // Créer l'adaptateur avec les données
        recyclerView.setAdapter(starAdapter); // Définir l'adaptateur pour le RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Définir le LayoutManager
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Code pour gérer la soumission de la recherche
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText); // Affiche la chaîne de recherche dans les logs
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText); // Appel du filtre
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


    public void init() {
        // Ajouter des clubs de la Premier League
        service.create(new Star("Manchester City", "https://upload.wikimedia.org/wikipedia/sco/thumb/e/eb/Manchester_City_FC_badge.svg/615px-Manchester_City_FC_badge.svg.png?20170428001246", 5));
        service.create(new Star("Liverpool", "https://upload.wikimedia.org/wikipedia/sco/thumb/0/0c/Liverpool_FC.svg/278px-Liverpool_FC.svg.png", 4.8f));
        service.create(new Star("Chelsea", "https://upload.wikimedia.org/wikipedia/fr/thumb/5/51/Logo_Chelsea.svg/315px-Logo_Chelsea.svg.png?20230217231922", 4.5f));
        service.create(new Star("Arsenal", "https://upload.wikimedia.org/wikipedia/fr/thumb/5/53/Arsenal_FC.svg/langfr-800px-Arsenal_FC.svg.png", 4.2f));
        service.create(new Star("Manchester United", "https://upload.wikimedia.org/wikipedia/fr/thumb/5/5b/Logo_Manchester_United_FC.svg/768px-Logo_Manchester_United_FC.svg.png?20240712112052", 4.7f));
        service.create(new Star("Tottenham Hotspur", "https://upload.wikimedia.org/wikipedia/fr/thumb/5/5c/Logo_Tottenham_Hotspur.svg/langfr-800px-Logo_Tottenham_Hotspur.svg.png", 4.4f));
    }

}
