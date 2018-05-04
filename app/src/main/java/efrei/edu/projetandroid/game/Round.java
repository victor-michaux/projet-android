package efrei.edu.projetandroid.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round implements Serializable{
    private List<Player> players;
    private Map<Player, PlayerRound> playerRoundDictionary;

    private boolean finished;

    public Round(){}

    public Round(final List<Player> players) {
        this.players = players;
        this.playerRoundDictionary = this.initPlayerRoundMap();
        this.finished = false;
    }

    public Round(final List<Player> players, final Map<Player, PlayerRound> playerRoundDictionary) {
        this.players = players;
        this.playerRoundDictionary = playerRoundDictionary;
        this.finished = this.getCurrentPlayer() == null;
    }

    private Map<Player, PlayerRound>initPlayerRoundMap()
    {
        HashMap<Player, PlayerRound> playerRoundMap = new HashMap<>();

        for (Player player : players)
        {
            playerRoundMap.put(player, new PlayerRound());
        }

        return playerRoundMap;
    }

    public PlayerRound getCurrentPlayerRound() {
        Player currentPlayer = this.getCurrentPlayer();

        // Si currentPlayer == null alors tous les joueurs on joué le tour
        if(currentPlayer == null) {
            this.finished = true;
            return null;
        }

        return this.playerRoundDictionary.get(currentPlayer);
    }

    public Player getCurrentPlayer()
    {
        for(Player player : this.players) {
            if(!this.playerRoundDictionary.get(player).isFinished()) {
                return player;
            }
        }

        return null;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
