package efrei.edu.projetandroid.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import efrei.edu.projetandroid.game.Game;

@Dao
public interface GameDao {
    @Insert
    void insert(Game game);
}
