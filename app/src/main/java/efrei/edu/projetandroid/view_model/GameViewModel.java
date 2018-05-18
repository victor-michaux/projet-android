package efrei.edu.projetandroid.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import efrei.edu.projetandroid.game.Game;
import efrei.edu.projetandroid.repository.GameRepository;

public class GameViewModel extends AndroidViewModel {

    private GameRepository gameRepository;

    public GameViewModel (Application application)
    {
        super(application);
        gameRepository = new GameRepository(application);
    }

    public void insert(Game game)
    {
        gameRepository.insert(game);
    }
}
