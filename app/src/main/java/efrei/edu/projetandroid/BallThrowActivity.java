package efrei.edu.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import efrei.edu.projetandroid.game.BallThrowType;
import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.Player;
import efrei.edu.projetandroid.game.PlayerRound;
import efrei.edu.projetandroid.game.Round;

public class BallThrowActivity extends AppCompatActivity {

    private Game game;
    private DatabaseReference mDatabase;
    private DatabaseReference mGamesRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_throw);

        Intent intent = getIntent();
        String gameUid = intent.getStringExtra("GameID");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGamesRef = mDatabase.child("games").child(gameUid);

        ValueEventListener gameListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                game = dataSnapshot.getValue(Game.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        mGamesRef.addListenerForSingleValueEvent(gameListener);

        // Check pour savoir si on affiche le fragment de saisie
/*        if(!game.isFinished()) {
            Round currentRound = game.getCurrentRound();
            PlayerRound currentPlayerRound = currentRound.getCurrentPlayerRound();

            // Première data bidon
            currentPlayerRound.play(BallThrowType.GUTTER, null);
        }*/
    }



}
