package efrei.edu.projetandroid.game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    static final int DEFAULT_ROUND_NUMBER = 3;

    private List<Player> players;
    private List<Round> rounds;

    // From Start Constructor
    public Game(final List<Player> players) {
        this.players = players;
        this.rounds = this.generateRoundList();
    }

    public Game(final List<Player> players, final ArrayList<Round> rounds) {
        this.players = players;
        this.rounds = rounds;
    }

    private ArrayList<Round> generateRoundList()
    {
        ArrayList<Round> roundArrayList = new ArrayList<>();

        for (int i = 0; i < DEFAULT_ROUND_NUMBER; i++)
        {
            roundArrayList.add(new Round(this.players));
        }

        return roundArrayList;
    }

    public Round getCurrentRound() {
        for (Round round : this.rounds) {
            if(!round.isFinished()) {
                return round;
            }
        }

        return null;
    }

    public boolean isFinished() {
        return this.getCurrentRound() == null;
    }
}
