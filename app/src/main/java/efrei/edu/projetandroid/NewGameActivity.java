package efrei.edu.projetandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewGameActivity extends AppCompatActivity {

    private List<Player> playerList;
    private ArrayList<String> listItems=new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        playerList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View prompt = li.inflate(R.layout.login_layout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewGameActivity.this);
                alertDialogBuilder.setView(prompt);

                final EditText nom = (EditText) prompt.findViewById(R.id.nom);
                final EditText prenom = (EditText) prompt.findViewById(R.id.prenom);

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                playerList.add(new Player(nom.getText().toString(),prenom.getText().toString()));
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


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        ListView listView = (ListView) findViewById(R.id.playerList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                // Event quand on clique sur un item
                // Faire sorte d'éditer le nom
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View prompt = li.inflate(R.layout.login_layout, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NewGameActivity.this);
                alertDialogBuilder.setView(prompt);

                final EditText nom = (EditText) prompt.findViewById(R.id.nom);
                final EditText prenom = (EditText) prompt.findViewById(R.id.prenom);

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
                System.out.println(position);
            }
        });
    }

    public void lunchGameHandler(View view){
        //When lunch game button as been clicked
        if(playerList.size() == 0){
            Context context = getApplicationContext();
            CharSequence text = "No player selected";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            // Lunch game activity
        }
    }
}
