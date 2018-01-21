package projetandroid.labyrinthe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 20/01/2018.
 */

public class CollisionManager {
    Bille player;
    ImageView maze;
    List<Point> perimeter;
    Activity gameActivity;

    public CollisionManager(Bille player, ImageView maze, Activity GA) {
        this.player = player;
        this.maze = maze;
        this.gameActivity = GA;
        this.perimeter=new ArrayList<Point>();
        fillPerimeter();
    }

    private void fillPerimeter() {
        Bitmap playerBM = BitmapFactory.decodeResource(this.gameActivity.getResources(), R.drawable.player);
        playerBM = Bitmap.createScaledBitmap(playerBM,40,40,false);
        for(int w=0;w<40;w++)
        {
            perimeter.add(getFirstOpaque(playerBM,w));
            perimeter.add(getLastOpaque(playerBM,w));
        }
        Log.w("List",perimeter.toString());
    }

    private Point getLastOpaque(Bitmap playerBM, int w) {
        for(int h=39;h>=0;h--)
        {
            int playerPX = playerBM.getPixel(w,h);
            int alphaPlayer = Color.alpha(playerPX);
            if(alphaPlayer!=0)
            {
                return new Point(w,h);
            }
        }
        return null;
    }

    private Point getFirstOpaque(Bitmap playerBM, int w) {
        for(int h=0;h<40;h++)
        {
            int playerPX = playerBM.getPixel(w,h);
            int alphaPlayer = Color.alpha(playerPX);
            if(alphaPlayer!=0)
            {
                return new Point(w,h);
            }
        }
        return null;
    }

    public void applyCollisionEffect()
    {
        // On charge le labyrinthe
        int w2;
        int h2;
        Bitmap mazeBM = ((BitmapDrawable)maze.getDrawable()).getBitmap();
        if(maze.getMeasuredWidth()==0)
        {
            w2=1050 ;
            h2=1444;
        }
        else
        {
            w2=maze.getMeasuredWidth();
            h2=maze.getMeasuredHeight();
        }
        Bitmap mazeBM2 = Bitmap.createScaledBitmap(mazeBM,w2,h2,false);

        // pour chaque pixel du perimètre du player, on vérifie la collision
        for(Point pxl:perimeter)
        {
            int mazePX = mazeBM2.getPixel(pxl.x-(int)maze.getX()+(int)player.getX(),pxl.y-(int)maze.getY()+(int)player.getY());
            int alphaMaze = Color.alpha(mazePX);
            if(alphaMaze!=0)
            {
                if(Math.abs(player.getVitX())>0.02)
                    player.setVitX(-player.getVitX());
                else
                    player.setVitX(0);
                if(Math.abs(player.getVitY())>0.02)
                    player.setVitY(-player.getVitY());
                else
                    player.setVitY(0);
                player.setAccX(0);
                player.setAccY(0);
                return;
                //Log.i("COLLISION","YES YES YES");
            }
        }
    }
}
