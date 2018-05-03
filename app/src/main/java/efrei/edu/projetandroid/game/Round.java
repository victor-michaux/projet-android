package efrei.edu.projetandroid.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {
    private List<Player> players;
    private Map<Player, PlayerRound> playerRoundDictionary;
    private PlayerRound currentPlayerRound = null;

    public Round(final List<Player> players) {
        this.players = players;
        this.playerRoundDictionary = this.initPlayerRoundMap();

    }

    public void runNewRound()
    {
        for(Player player : this.players)
        {

        }
    }

    private Map<Player, PlayerRound>initPlayerRoundMap()
    {
        HashMap<Player, PlayerRound> playerRoundMap = new HashMap<>();

        for (Player player : players)
        {
            playerRoundMap.put(player, null);
        }

        return playerRoundMap;
    }

    public PlayerRound getCurrentPlayerRound() {
        for(Player player : this.players) {
            if(this.playerRoundDictionary.get(player) == null) {
                return
            }
        }
    }

    private Player getCurrentPlayer()
    {
        for(Player player : this.players) {
            if(this.playerRoundDictionary.get(player) == null) {
                return player;
            }
        }

        return null;
    }

    public void nextPlayer() {
        int playerCount = this.players.size();
        this.playerRoundDictionary.get(this.currentPlayerRound)
    }
}
