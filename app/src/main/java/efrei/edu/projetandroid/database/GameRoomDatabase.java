package efrei.edu.projetandroid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import efrei.edu.projetandroid.dao.GameDao;
import efrei.edu.projetandroid.game.Game;

@Database(entities = {Game.class}, version = 2)
public abstract class GameRoomDatabase extends RoomDatabase {

    private static GameRoomDatabase instance;

    public static GameRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (GameRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            GameRoomDatabase.class, "game_database")
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract GameDao gameDao();
}
