package efrei.edu.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void newGameOnclick(View view){
        Intent newGameIntent = new Intent(this, NewGameActivity.class);
        startActivity(newGameIntent);
    }

    public void onOldGamesClick(View view)
    {
        Intent oldGameIntent = new Intent(this, OldGameActivity.class);
        startActivity(oldGameIntent);
    }

    // Faudra charger les données du game en cours dans l'activité
    public void onGameResumeClick(View view)
    {
        Intent gameResumeIntent = new Intent(this, BallThrowActivity.class);
        startActivity(gameResumeIntent);
    }
}
