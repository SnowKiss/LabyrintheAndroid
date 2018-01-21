package projetandroid.labyrinthe;

import android.widget.ImageView;

/**
 * Created by John on 19/01/2018.
 */

public class Bille {
    private float x;
    private float y;

    private float vitX;
    private float vitY;

    private float accX;
    private float accY;

    ImageView vue;

    public Bille(ImageView vue) {
        this.x = 100;
        this.y = 100;
        this.vitX=0;
        this.vitY=0;
        this.accX=0;
        this.accY=0;
        this.vue=vue;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVitX() {
        return vitX;
    }

    public void setVitX(float vitX) {
        this.vitX = vitX;
    }

    public float getVitY() {
        return vitY;
    }

    public void setVitY(float vitY) {
        this.vitY = vitY;
    }

    public float getAccX() {
        return accX;
    }

    public void setAccX(float accX) {
        this.accX = accX;
    }

    public float getAccY() {
        return accY;
    }

    public void setAccY(float accY) {
        this.accY = accY;
    }

    public void updateView()
    {   
        this.setVitX(this.getVitX()+this.getAccX());
        this.setVitY(this.getVitY()+this.getAccY());
        if(Math.abs(this.getVitX())<0.02f)
            this.setVitX(0);
        if(Math.abs(this.getVitY())<0.02f)
            this.setVitY(0);

        // on met à jour les coordonnées
        this.setX(this.getX()+this.getVitX());
        this.setY(this.getY()+this.getVitY());

        // update la vue
        vue.setX(this.x);
        vue.setY(this.y);
    }

    public ImageView getVue() {
        return vue;
    }
}
