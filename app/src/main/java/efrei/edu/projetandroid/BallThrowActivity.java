package efrei.edu.projetandroid;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import efrei.edu.projetandroid.fragment.PlayerThrowFragment;
import efrei.edu.projetandroid.game.BallThrowType;
import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.Player;
import efrei.edu.projetandroid.game.PlayerRound;
import efrei.edu.projetandroid.game.Round;
import efrei.edu.projetandroid.view_model.GameViewModel;

public class BallThrowActivity extends AppCompatActivity implements PlayerThrowFragment.OnFragmentInteractionListener {

    private Game game;
    // Write a message to the database
    private DatabaseReference mGameReference;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private GameViewModel gameViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_throw);

        Intent intent = getIntent();
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        //Game game = (Game) intent.getSerializableExtra("Game");

        //mGameReference = FirebaseDatabase.getInstance().getReference().child("games").child(game.getUid());

        /*mGameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        //mGamesRef.addListenerForSingleValueEvent(gameListener);

        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = fragmentManager.beginTransaction();

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Michaux", "Victor"));
        players.add(new Player("Boukari", "Bryan"));
        this.game = new Game(players);

        gameViewModel.insert(game);

        // Check pour savoir si on affiche le fragment de saisie
        if(!game.isFinished()) {
            // Récupère round et player round
            Round currentRound = game.getCurrentRound();
            PlayerRound currentPlayerRound = currentRound.getCurrentPlayerRound();

            PlayerThrowFragment playerThrowFragment = PlayerThrowFragment.newInstance(currentRound, currentPlayerRound);
            fragmentTransaction.add(R.id.playerThrowFragmentContainer, playerThrowFragment);
            fragmentTransaction.commit();

            // Première data bidon
            currentPlayerRound.play(BallThrowType.GUTTER, null);

            //mGameReference.setValue(game);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Round currentRound = game.getCurrentRound();
        PlayerRound currentPlayerRound = currentRound.getCurrentPlayerRound();

        if(currentPlayerRound == null) {
            currentRound = game.getCurrentRound();
            currentPlayerRound = currentRound.getCurrentPlayerRound();
        }

        PlayerThrowFragment playerThrowFragment = PlayerThrowFragment.newInstance(currentRound, currentPlayerRound);
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.playerThrowFragmentContainer, playerThrowFragment);
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
