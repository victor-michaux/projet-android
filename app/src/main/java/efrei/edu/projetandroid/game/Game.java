package efrei.edu.projetandroid.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    static final int DEFAULT_ROUND_NUMBER = 3;

    private List<Player> players;
    private List<Round> rounds;
    private Round currentRound = null;

    // From Start Constructor
    public Game(final List<Player> players) {
        this.players = players;
        this.rounds = this.generateRoundList();
    }

    public void runNewgame() {
        for (Round round : this.rounds) {
            //Launch Round
        }
    }

    private ArrayList<Round> generateRoundList()
    {
        ArrayList<Round> roundArrayList = new ArrayList<>();

        for (int i = 0; i < DEFAULT_ROUND_NUMBER; i++)
        {
            roundArrayList.add(new Round(this.players));
        }

        this.currentRound = roundArrayList.get(0);

        return roundArrayList;
    }

    public Round getCurrentRound() {
        return this.currentRound;
    }
}
