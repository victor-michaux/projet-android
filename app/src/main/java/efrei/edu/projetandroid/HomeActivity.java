package efrei.edu.projetandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onOldGamesClick(View view)
    {
        Intent oldGameIntent = new Intent(this, OldGameActivity.class);
        startActivity(oldGameIntent);
    }
}
