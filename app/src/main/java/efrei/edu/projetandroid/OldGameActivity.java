package efrei.edu.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.Player;
import efrei.edu.projetandroid.game.Round;

public class OldGameActivity extends AppCompatActivity {

    private DatabaseReference mGameReference;
    private List<Game> gameList;
    private List<String> gameInfoList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_old_game);

        gameList = new ArrayList<>();

        gameInfoList = new ArrayList<>();

        mGameReference = FirebaseDatabase.getInstance().getReference().child("games");

        mGameReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mapGames((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ListView listView = (ListView) findViewById(R.id.oldGamesList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameInfoList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                // Event quand on clique sur un item
                System.out.println(position);
            }
        });
    }

    private void mapGames(Map<String,Object> objectMap){
        for (Map.Entry<String, Object> entry : objectMap.entrySet()){
            //Get game map
            Map singleGame = (Map) entry.getValue();
            Game game = new Game();
            game.setUid((String) singleGame.get("uid"));
            game.setPlayers((List<Player>) singleGame.get("players"));
            game.setRounds((List<Round>) singleGame.get("rounds"));
            game.setDate((String) singleGame.get("date"));
            game.setAddress((String) singleGame.get("address"));
            gameList.add(game);
            gameInfoList.add("Partie du : " + game.getDate() + ", joué à : " + game.getAddress());
            adapter.notifyDataSetChanged();
        }
    }
}
