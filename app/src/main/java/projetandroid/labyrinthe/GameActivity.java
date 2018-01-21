package projetandroid.labyrinthe;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.os.Handler;

public class GameActivity extends Activity {

    Uri maze;
    InputManager inputManager;
    Bille player;
    PhysicManager physicManager;
    private CollisionManager collisionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        inputManager = new InputManager(this);
        player = new Bille((ImageView) findViewById(R.id.player));
        physicManager = new PhysicManager(player, inputManager);
        this.collisionManager = new CollisionManager(player, (ImageView) findViewById(R.id.maze), this);
        final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                physicManager.moveBall();
                collisionManager.applyCollisionEffect();
                player.updateView();
                handler.postDelayed(this,1); // set time here to refresh textView
            }
        });
    }

}
