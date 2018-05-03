package efrei.edu.projetandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import efrei.edu.projetandroid.game.BallThrowType;
import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.game.Player;
import efrei.edu.projetandroid.game.PlayerRound;
import efrei.edu.projetandroid.game.Round;

public class BallThrowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_throw);

        boolean finished = false;
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Michaux", "Victor"));
        players.add(new Player("Boukari", "Bryan"));

        Game game = new Game(players);

        // Check pour savoir si on affiche le fragment de saisie
        if(!game.isFinished()) {
            Round currentRound = game.getCurrentRound();
            PlayerRound currentPlayerRound = currentRound.getCurrentPlayerRound();

            // Première data bidon
            currentPlayerRound.play(BallThrowType.GUTTER, null);
        }
    }
}
