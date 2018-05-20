package efrei.edu.projetandroid.game;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "games")
public class Game implements Serializable{
    static final int DEFAULT_ROUND_NUMBER = 3;

    @PrimaryKey
    private int id;

    @Ignore
    private List<Player> players;

    @Ignore
    private List<Round> rounds;

    private String uid;

    private String address;

    private String date;

    // From Start Constructor
    public Game(final List<Player> players) {
        this.players = players;
        this.rounds = this.generateRoundList();
    }

    public Game(){}

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
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

    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
