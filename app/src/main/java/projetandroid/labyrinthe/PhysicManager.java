package projetandroid.labyrinthe;

import android.util.Log;

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
        bille.setVitX(bille.getVitX()+bille.getAccX());
        bille.setVitY(bille.getVitY()+bille.getAccY());
        if(Math.abs(bille.getVitX())<0.02f)
            bille.setVitX(0);
        if(Math.abs(bille.getVitY())<0.02f)
            bille.setVitY(0);

        // on met à jour les coordonnées
        bille.setX(bille.getX()+bille.getVitX());
        bille.setY(bille.getY()+bille.getVitY());

        // update la vue
        bille.updateView();
    }
}
