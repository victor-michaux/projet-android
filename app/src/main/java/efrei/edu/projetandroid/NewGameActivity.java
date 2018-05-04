package efrei.edu.projetandroid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.Player;

public class NewGameActivity extends AppCompatActivity {

    private Game game;
    private List<Player> playerList;
    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    // Write a message to the database
    private DatabaseReference mDatabase;
    private FusedLocationProviderClient mFusedLocationClient;
    // Address of the game
    private String address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        ActivityCompat.requestPermissions(this,
             new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
             123);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        playerList = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Gestion du bouton floatant
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View prompt = li.inflate(R.layout.login_layout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewGameActivity.this);
                alertDialogBuilder.setView(prompt);

                final EditText nom = prompt.findViewById(R.id.nom);
                final EditText prenom = prompt.findViewById(R.id.prenom);

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                playerList.add(new Player(nom.getText().toString(), prenom.getText().toString()));
                                listItems.add(nom.getText().toString() + " " + prenom.getText().toString());
                                adapter.notifyDataSetChanged();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                alertDialogBuilder.show();
            }
        });

        // Gestion de la liste
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        ListView listView = findViewById(R.id.playerList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                // Event quand on clique sur un item
                // Faire sorte d'éditer le nom
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View prompt = li.inflate(R.layout.login_layout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewGameActivity.this);
                alertDialogBuilder.setView(prompt);

                final EditText nom = prompt.findViewById(R.id.nom);
                final EditText prenom = prompt.findViewById(R.id.prenom);

                nom.setText(playerList.get(position).getNom());
                prenom.setText(playerList.get(position).getPrenom());

                alertDialogBuilder.setCancelable(true)
                        .setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Player player = playerList.get(position);
                                player.setNom(nom.getText().toString());
                                player.setPrenom(prenom.getText().toString());
                                listItems.set(position, player.getNom() + " " + player.getPrenom());
                                adapter.notifyDataSetChanged();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        playerList.remove(position);
                        listItems.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDialogBuilder.show();
            }
        });
    }

    public void lunchGameHandler(View view) {
        //When lunch game button as been clicked
        if (playerList.size() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "No player selected";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            game = new Game(playerList);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Geocoder geocoder;
                                List<Address> addresses = null;
                                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                address = addresses.get(0).getAddressLine(0);
                                game.setAddress(address);
                                game.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                                game.setUid(mDatabase.child("games").push().getKey());
                                mDatabase.child("games").child(game.getUid()).setValue(game);
                                lunchBallThrowActivity();
                            }
                        }
                    });
        }
    }

    private void lunchBallThrowActivity(){
        Intent gameIntent = new Intent(this, BallThrowActivity.class);
        gameIntent.putExtra("Game", game);
        startActivity(gameIntent);
    }

}
