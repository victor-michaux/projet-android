package efrei.edu.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import efrei.edu.projetandroid.game.BallThrowType;
import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.PlayerRound;
import efrei.edu.projetandroid.game.Round;

public class BallThrowActivity extends AppCompatActivity {

    private Game game;
    // Write a message to the database
    private DatabaseReference mGameReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_throw);

        Intent intent = getIntent();
        Game game = (Game) intent.getSerializableExtra("Game");

        mGameReference = FirebaseDatabase.getInstance().getReference().child("games").child(game.getUid());

        mGameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Check pour savoir si on affiche le fragment de saisie
        if(!game.isFinished()) {
            Round currentRound = game.getCurrentRound();
            PlayerRound currentPlayerRound = currentRound.getCurrentPlayerRound();

            // Première data bidon
            currentPlayerRound.play(BallThrowType.GUTTER, null);
            mGameReference.setValue(game);
        }
    }



}
