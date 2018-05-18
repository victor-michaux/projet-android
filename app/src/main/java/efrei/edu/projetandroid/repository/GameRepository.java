package efrei.edu.projetandroid.repository;

import android.app.Application;
import android.os.AsyncTask;

import efrei.edu.projetandroid.dao.GameDao;
import efrei.edu.projetandroid.database.GameRoomDatabase;
import efrei.edu.projetandroid.game.Game;

public class GameRepository {
    private GameDao gameDao;

    public GameRepository(Application application) {
        GameRoomDatabase db = GameRoomDatabase.getDatabase(application);
        gameDao = db.gameDao();
    }

    public void insert (Game game) {
        new insertAsyncTask(gameDao).execute(game);
    }

    private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao asyncGameDao;

        insertAsyncTask(GameDao dao) {
            asyncGameDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            asyncGameDao.insert(params[0]);
            return null;
        }
    }
}
