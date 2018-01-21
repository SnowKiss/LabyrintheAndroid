package projetandroid.labyrinthe;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by John on 19/01/2018.
 */

public class PhysicManager {

    private Bille bille;
    private InputManager inputManager;
    private float coefInercie = 0.025f;
    private boolean frottement = true;
    private float coefFrot = 0.992f;


    public PhysicManager(Bille bille, InputManager inputManager) {
        this.bille = bille;
        this.inputManager = inputManager;

    }

    public void moveBall()
    {
        //on met à jour l'acceleration
        bille.setAccX(inputManager.getDeltaX()*coefInercie);
        bille.setAccY(inputManager.getDeltaY()*coefInercie);

        // on met à jour la vitesse
        if(frottement)
        {
            bille.setVitX(bille.getVitX()*coefFrot);
            bille.setVitY(bille.getVitY()*coefFrot);
        }

    }


}
