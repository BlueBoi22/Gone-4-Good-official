package org.cistercian.franco;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Rectangle{
    public Vector2 dif = new Vector2();
    float velocity = 10f;
    boolean dead = false;
    public void update(ArrayList<Bullet> bulletlist){
        this.x += dif.x * velocity;
        this.y += dif.y * velocity;
        for (Bullet i : bulletlist) {
            if (i.bulletpenetration  > 0) {
            if (this.overlaps(i)) {
                dead = true;
                i.bulletpenetration--;
            }
            
        }
        }

        
   }


    Zombie(float x, float y,float endX, float endY){
        super(x, y, 10, 10);
        width = 184;
        height = 184;
        
        dif = new Vector2(endX, endY).nor();
    }   
}